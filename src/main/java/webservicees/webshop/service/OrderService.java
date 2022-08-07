package webservicees.webshop.service;

import webservicees.webshop.model.*;
import webservicees.webshop.repository.CustomerRepository;
import webservicees.webshop.repository.OrderPositionRepository;
import webservicees.webshop.repository.OrderRepository;
import webservicees.webshop.repository.ProductRepository;

import java.util.Optional;
import java.util.UUID;

public class OrderService {

    OrderRepository orderRepository = new OrderRepository();
    CustomerRepository customerRepository = new CustomerRepository();
    OrderPositionRepository orderPositionRepository = new OrderPositionRepository();
    ProductRepository productRepository = new ProductRepository(); // second instances, requests will not work properly

    public OrderResponse createOrder(OrderCreateRequest request) {
        Optional<CustomerResponse> customer = customerRepository.findById(request.getCustomerId());

        if (customer.isEmpty())
            throw new RuntimeException("Customer not found!");
        return orderRepository.save(request);
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
