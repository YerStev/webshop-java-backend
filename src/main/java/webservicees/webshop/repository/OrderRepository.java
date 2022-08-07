package webservicees.webshop.repository;

import webservicees.webshop.model.OrderCreateRequest;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.OrderResponse;
import webservicees.webshop.model.OrderStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class
OrderRepository {

    List<OrderResponse> orders = new  ArrayList<>();

    public OrderResponse save(OrderCreateRequest request) {
        OrderResponse orderResponse = new OrderResponse(
                UUID.randomUUID().toString(),
                request.getCustomerId(),
                new Timestamp(System.currentTimeMillis()),
                OrderStatus.NEW,
                new ArrayList<>()
        );
        orders.add(orderResponse);
        return orderResponse;
    }

    public Optional<OrderResponse> findById(String orderId) {
        return orders.stream().
                filter(s -> s.getId().equals(orderId)).
                findFirst();
    }
}
