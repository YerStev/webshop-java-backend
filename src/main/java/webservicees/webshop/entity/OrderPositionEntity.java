package webservicees.webshop.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_positions")
public class OrderPositionEntity {
    @Id
        private String id;
        private String productId;
        private String orderId;
        private long quantity;



        public OrderPositionEntity(String id, String productId, String orderId, long quantity) {
            this.id = id;
            this.productId = productId;
            this.orderId = orderId;
            this.quantity = quantity;
        }

    public OrderPositionEntity() {

    }

    public String getOrderId() {
            return orderId;
        }

        public String getId() {
            return id;
        }

        public String getProductId() {
            return productId;
        }

        public long getQuantity() {
            return quantity;
        }
    }
