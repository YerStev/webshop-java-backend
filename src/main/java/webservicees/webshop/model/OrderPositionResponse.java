package webservicees.webshop.model;

public class OrderPositionResponse {
    private String id;
    private String productId;
    private long quantity;

    public OrderPositionResponse(String id, String productId, long quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getId() {
        return id;
    }

    public long getQuantity() {
        return quantity;
    }
}
