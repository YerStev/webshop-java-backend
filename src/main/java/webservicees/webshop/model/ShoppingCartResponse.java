package webservicees.webshop.model;

import java.util.List;

public class ShoppingCartResponse {
    private String customerId;
    private List<OrderPositionResponse> orderPositions;
    private long totalAmountInCent;
    private long deliveryCostInCent;
    private String deliveryOption;

    public ShoppingCartResponse(String customerId, List<OrderPositionResponse> orderPositions, long totalAmountInCent, long deliveryCostInCent, String deliveryOption) {
        this.customerId = customerId;
        this.orderPositions = orderPositions;
        this.totalAmountInCent = totalAmountInCent;
        this.deliveryCostInCent = deliveryCostInCent;
        this.deliveryOption = deliveryOption;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderPositionResponse> getOrderPositions() {
        return orderPositions;
    }

    public long getTotalAmountInCent() {
        return totalAmountInCent;
    }

    public long getDeliveryCostInCent() {
        return deliveryCostInCent;
    }

    public String getDeliveryOption() {
        return deliveryOption;
    }
}
