package webservicees.webshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webservicees.webshop.model.ErrorResponse;
import webservicees.webshop.model.OrderCreateRequest;
import webservicees.webshop.model.OrderPositionCreateRequest;
import webservicees.webshop.service.OrderService;

import java.util.List;
import java.util.Objects;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderCreateRequest request){
        OrderService.CreateOrderResult orderResult = orderService.createOrder(request);
        if(orderResult.getResult() != null)
            return ResponseEntity.ok(orderResult.getResult());

        return ResponseEntity.status(orderResult.getCode()).body(orderResult.getErrors());
    }

    @PostMapping("/orders/{id}/positions")
    public void createOrderPosition(@PathVariable(name = "id") String orderId, @RequestBody OrderPositionCreateRequest request){
        orderService.createPositionForOrder(orderId, request);
    }
}
