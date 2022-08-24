package webservicees.webshop.service;

import webservicees.webshop.exceptions.WebshopException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webservicees.webshop.model.*;
import webservicees.webshop.repository.CustomerRepository;
import webservicees.webshop.repository.OrderPositionRepository;
import webservicees.webshop.repository.OrderRepository;
import webservicees.webshop.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderPositionRepository orderPositionRepository;


    public OrderService(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository, OrderPositionRepository orderPositionRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderPositionRepository = orderPositionRepository;
    }

    public OrderResponse createOrder(OrderCreateRequest request) throws WebshopException {
        Optional<CustomerResponse> customer = customerRepository.findById(request.getCustomerId());

        if (customer.isEmpty())
            throw new WebshopException("Customer with " + request.getCustomerId() + " id not found", HttpStatus.BAD_REQUEST);

        return orderRepository.save(request);
    }

    public OrderPositionResponse createPositionForOrder(String orderId, OrderPositionCreateRequest request) {
        Optional <OrderResponse> order = orderRepository.findById(orderId);
        if(order.isEmpty())
            throw new WebshopException("Order with " + orderId + " id not found", HttpStatus.BAD_REQUEST);

        Optional<ProductResponse> product = productRepository.findById(request.getProductId());
        if(product.isEmpty())
            throw new WebshopException("Product with " + request.getProductId() + " id not found", HttpStatus.BAD_REQUEST);

        OrderPositionResponse orderPositionResponse = new OrderPositionResponse(
                UUID.randomUUID().toString(),
                request.getProductId(),
                request.getQuantity());

        orderPositionRepository.save(orderPositionResponse);
        return orderPositionResponse;
    }
}
