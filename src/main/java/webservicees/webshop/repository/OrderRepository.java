package webservicees.webshop.repository;

import org.springframework.stereotype.Service;
import webservicees.webshop.model.OrderCreateRequest;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.OrderResponse;
import webservicees.webshop.model.OrderStatus;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderRepository {

   private List<OrderResponse> orders = new  ArrayList<>();

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

    public List<OrderResponse> findAllByCustomerIdWhereOrderStatusIsNew(String customerId) {
        return orders.stream()
                .filter(o -> o.getCustomerId().equals(customerId) && o.getStatus() == OrderStatus.NEW)
                .collect(Collectors.toList());
    }
}
