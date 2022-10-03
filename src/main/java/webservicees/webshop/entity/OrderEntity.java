package webservicees.webshop.entity;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.OrderStatus;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    private String id;
    private String customerId;
    private Timestamp orderTime;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderEntity(String id, String customerId, Timestamp orderTime, OrderStatus status) {
        this.id = id;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.status = status;
    }

    public OrderEntity() {
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

}
