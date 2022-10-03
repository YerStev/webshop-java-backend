package webservicees.webshop.controller;

import org.springframework.web.bind.annotation.*;
import webservicees.webshop.model.*;
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

    @PutMapping("/orders/{id}")
    public OrderResponse updateOrder(@PathVariable String id, @RequestBody OrderUpdateRequest request){
        return orderService.updateOrder(id, request);
    }
}
