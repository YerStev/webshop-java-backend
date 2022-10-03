package webservicees.webshop.model;

public class OrderUpdateRequest {
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    public OrderUpdateRequest(OrderStatus status) {
        this.status = status;
    }

    public OrderUpdateRequest() {
    }


}
