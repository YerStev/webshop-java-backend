package webservicees.webshop.model;

public class OrderPositionCreateRequest {
    private String productId;
    private long quantity;

    public OrderPositionCreateRequest() {
    }

    public OrderPositionCreateRequest(String productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }
}
