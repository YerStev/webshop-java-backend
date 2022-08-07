package webservicees.webshop.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderResponse {
    private String id;
    private String customerId;
    private Timestamp orderTime;
    private OrderStatus status;
    private List<OrderPositionResponse> orderPositions;

    public OrderResponse(String id, String customerId, Timestamp orderTime, OrderStatus status, List<OrderPositionResponse> orderPositions) {
        this.id = id;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.status = status;
        this.orderPositions = orderPositions;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderPositionResponse> getOrderPositions() {
        return orderPositions;
    }


}


