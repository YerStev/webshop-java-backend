package webservicees.webshop.model;

public class OrderPositionResponse {
    private String id;
    private String productId;
    private String orderId;
    private long quantity;

    public OrderPositionResponse(String id, String productId, String orderId, long quantity) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
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
