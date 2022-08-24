package webservicees.webshop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webservicees.webshop.model.OrderCreateRequest;
import webservicees.webshop.model.OrderPositionCreateRequest;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.OrderResponse;
import webservicees.webshop.service.OrderService;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request){
            return orderService.createOrder(request);
    }

    @PostMapping("/orders/{id}/positions")
    public OrderPositionResponse createOrderPosition(@PathVariable(name = "id") String orderId, @RequestBody OrderPositionCreateRequest request) {
        return orderService.createPositionForOrder(orderId, request);
    }
}
