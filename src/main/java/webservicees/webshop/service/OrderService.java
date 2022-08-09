package webservicees.webshop.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webservicees.webshop.model.*;
import webservicees.webshop.repository.CustomerRepository;
import webservicees.webshop.repository.OrderPositionRepository;
import webservicees.webshop.repository.OrderRepository;
import webservicees.webshop.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderPositionRepository orderPositionRepository;

    public class CreateOrderResult{
        private OrderResponse result;
        private HttpStatus code;
        private List<ErrorResponse> errors;

        public CreateOrderResult(OrderResponse result, HttpStatus code, List<ErrorResponse> errors) {
            this.result = result;
            this.code = code;
            this.errors = errors;
        }

        public OrderResponse getResult() {
            return result;
        }

        public List<ErrorResponse> getErrors() {
            return errors;
        }

        public HttpStatus getCode() {
            return code;
        }
    }

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository, CustomerRepository customerRepository, OrderPositionRepository orderPositionRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderPositionRepository = orderPositionRepository;
    }

    public CreateOrderResult createOrder(OrderCreateRequest request) {
        Optional<CustomerResponse> customer = customerRepository.findById(request.getCustomerId());

        if (customer.isEmpty()){
            List<ErrorResponse> errorResponses = List.of(new ErrorResponse(HttpStatus.BAD_REQUEST, "Customer with id " + request.getCustomerId() + " not found"));
            return new CreateOrderResult(null, HttpStatus.BAD_REQUEST, errorResponses);
        }

        OrderResponse result = orderRepository.save(request);
        return new CreateOrderResult(result, HttpStatus.OK, new ArrayList<>());
    }

    public OrderPositionResponse createPositionForOrder(String orderId, OrderPositionCreateRequest request) {
        Optional <OrderResponse> order = orderRepository.findById(orderId);
        if(order.isEmpty())
            throw new RuntimeException("Order not found");

        Optional<ProductResponse> product = productRepository.findById(request.getProductId());
        if(product.isEmpty())
            throw new RuntimeException("Product not found");

        OrderPositionResponse orderPositionResponse = new OrderPositionResponse(
                UUID.randomUUID().toString(),
                request.getProductId(),
                request.getQuantity());

        orderPositionRepository.save(orderPositionResponse);
        return orderPositionResponse;
    }
}
