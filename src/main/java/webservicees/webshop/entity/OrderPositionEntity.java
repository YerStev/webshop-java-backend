package webservicees.webshop.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OrderPositionEntity {
        private String id;
        private String productId;
        private long quantity;



        public OrderPositionEntity(String id, String productId,  long quantity) {
            this.id = id;
            this.productId = productId;
            this.quantity = quantity;
        }

    public OrderPositionEntity() {

    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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
