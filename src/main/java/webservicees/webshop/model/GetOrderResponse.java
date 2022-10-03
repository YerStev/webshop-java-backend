package webservicees.webshop.model;

import jdk.jshell.Snippet;

import java.sql.Timestamp;
import java.util.List;

public class GetOrderResponse {
    private String id;
    private Timestamp orderTime;
    private OrderStatus status;
    private CustomerResponse customer;
    private List<GetOrderPositionResponse> orderPositions;

    public GetOrderResponse(String id, Timestamp orderTime, OrderStatus status, CustomerResponse customer, List<GetOrderPositionResponse> orderPositions) {
        this.id = id;
        this.orderTime = orderTime;
        this.status = status;
        this.customer = customer;
        this.orderPositions = orderPositions;
    }

    public String getId() {
        return id;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public CustomerResponse getCustomer() {
        return customer;
    }

    public List<GetOrderPositionResponse> getOrderPositions() {
        return orderPositions;
    }
}
