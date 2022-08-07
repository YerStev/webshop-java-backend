package webservicees.webshop.model;

public class OrderCreateRequest {
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public OrderCreateRequest(String customerId) {
        this.customerId = customerId;
    }

    public OrderCreateRequest() {
    }
}
