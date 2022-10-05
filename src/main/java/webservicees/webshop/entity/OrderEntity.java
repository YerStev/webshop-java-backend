package webservicees.webshop.entity;
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
    @ElementCollection
    @CollectionTable(name = "ORDER_POSITIONS", joinColumns = @JoinColumn(name = "ORDER_ENTITY_ID", referencedColumnName = "id"))
    private List<OrderPositionEntity> orderPositionEntities;

    public OrderEntity(String id, String customerId, Timestamp orderTime, OrderStatus status, List<OrderPositionEntity> orderPositionEntities) {
        this.id = id;
        this.customerId = customerId;
        this.orderTime = orderTime;
        this.status = status;
        this.orderPositionEntities = orderPositionEntities;
    }

    public OrderEntity() {
    }

    public List<OrderPositionEntity> getOrderPositions() {
        return orderPositionEntities;
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
