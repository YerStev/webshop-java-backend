package webservicees.webshop.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webservicees.webshop.model.OrderCreateRequest;
import webservicees.webshop.model.OrderPositionCreateRequest;
import webservicees.webshop.model.OrderResponse;
import webservicees.webshop.repository.OrderRepository;
import webservicees.webshop.service.OrderService;

@RestController
public class OrderController {

    OrderService orderService = new OrderService();

    @PostMapping("/orders")
    public OrderResponse createOrder( @RequestBody OrderCreateRequest request){
       return orderService.createOrder(request);
    }

    @PostMapping("/orders/{id}/positions")
    public void createOrderPosition(@PathVariable(name = "id") String orderId, @RequestBody OrderPositionCreateRequest request){
        orderService.createPositionForOrder(orderId, request);
    }
}
