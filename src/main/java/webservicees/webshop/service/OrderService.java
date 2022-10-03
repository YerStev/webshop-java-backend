package webservicees.webshop.service;

import webservicees.webshop.entity.CustomerEntity;
import webservicees.webshop.entity.OrderEntity;
import webservicees.webshop.entity.OrderPositionEntity;
import webservicees.webshop.entity.ProductEntity;
import webservicees.webshop.exception.IdNotFoundException;
import webservicees.webshop.exception.WebshopException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webservicees.webshop.model.*;
import webservicees.webshop.repository.ICustomerRepository;
import webservicees.webshop.repository.IOrderRepository;
import webservicees.webshop.repository.IOrderPositionRepository;
import webservicees.webshop.repository.IProductRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final IProductRepository productRepository;
    private final IOrderRepository orderRepository;
    private final ICustomerRepository customerRepository;
    private final IOrderPositionRepository orderPositionRepository;


    public OrderService(IProductRepository productRepository, IOrderRepository orderRepository, ICustomerRepository customerRepository, IOrderPositionRepository orderPositionRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderPositionRepository = orderPositionRepository;
    }

    public CreateOrderResponse createOrder(OrderCreateRequest request) throws WebshopException {
        Optional<CustomerEntity> customer = customerRepository.findById(request.getCustomerId());
        if (customer.isEmpty())
            throw new WebshopException("Order with " + request.getCustomerId() + " id not found", HttpStatus.BAD_REQUEST);

        OrderEntity order = new OrderEntity(
                UUID.randomUUID().toString(),
                request.getCustomerId(),
                new Timestamp(System.currentTimeMillis()),
                OrderStatus.NEW
        );
        OrderEntity savedOrder = orderRepository.save(order);

        return mapToResponse(savedOrder);
    }

    private CreateOrderResponse mapToResponse(OrderEntity savedOrder) {
        return new CreateOrderResponse(
                savedOrder.getId(),
                savedOrder.getCustomerId(),
                savedOrder.getOrderTime(),
                savedOrder.getStatus(),
                new ArrayList<>()
        );
    }

    public OrderPositionResponse createPositionForOrder(String orderId, OrderPositionCreateRequest request) {
        Optional<OrderEntity> order = orderRepository.findById(orderId);
        if (order.isEmpty())
            throw new WebshopException("Order with " + orderId + " id not found", HttpStatus.BAD_REQUEST);

        Optional<ProductEntity> product = productRepository.findById(request.getProductId());
        if (product.isEmpty())
            throw new WebshopException("Product with " + request.getProductId() + " id not found", HttpStatus.BAD_REQUEST);

        OrderPositionEntity orderPositionResponse = new OrderPositionEntity(
                UUID.randomUUID().toString(),
                request.getProductId(),
                orderId,
                request.getQuantity());

        OrderPositionEntity saved = orderPositionRepository.save(orderPositionResponse);
        return mapToOrderPositionResponse(saved);
    }

    public static OrderPositionResponse mapToOrderPositionResponse(OrderPositionEntity saved) {
        return new OrderPositionResponse(saved.getId(), saved.getProductId(), saved.getOrderId(), saved.getQuantity());
    }

    public CreateOrderResponse updateOrder(String id, OrderUpdateRequest request) {
        OrderEntity order = orderRepository.findById(id).
                orElseThrow(() -> new IdNotFoundException("Product with id " + id + " not found", HttpStatus.BAD_REQUEST));
        OrderEntity updated = new OrderEntity(
                order.getId(),
                order.getCustomerId(),
                order.getOrderTime(),
                order.getStatus() == null ? order.getStatus() : request.getStatus()
        );
        OrderEntity saved = orderRepository.save(updated);
        return mapToResponse(saved);
    }

    public GetOrderResponse getOrder(String id) {
        OrderEntity order = orderRepository
                .findById(id)
                .orElseThrow(() -> new WebshopException
                        ("Order with " + id + " id not found",
                                HttpStatus.BAD_REQUEST));

        CustomerEntity customer = customerRepository
                .findById(order.getCustomerId())
                .orElseThrow(() -> new WebshopException
                        ("Customer with " + order.getCustomerId() + " id not found",
                                HttpStatus.BAD_REQUEST));

        List<GetOrderPositionResponse> positions = orderPositionRepository
                .findAll()
                .stream()
                .filter(orderPositions -> orderPositions.getOrderId().equals(id))
                .map(foundOrderPositions -> {
                    ProductEntity product = productRepository
                            .findById(foundOrderPositions.getProductId())
                            .orElseThrow(() -> new WebshopException("Product with " + id + " id not found found", HttpStatus.BAD_REQUEST));
                    return new GetOrderPositionResponse(foundOrderPositions.getId(),
                            new ProductResponse(product.getId(),
                                    product.getName(),
                                    product.getDescription(),
                                    product.getPriceInCent(),
                                    product.getTags()
                            ),
                            foundOrderPositions.getQuantity());
                })
                .collect(Collectors.toList());

        return new GetOrderResponse(order.getId(),
                order.getOrderTime(),
                order.getStatus(),
                new CustomerResponse(
                        customer.getId(),
                        customer.getFirstName(),
                        customer.getLastName(),
                        customer.getEmail()),
                positions);
    }
}
