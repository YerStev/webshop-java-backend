package webservicees.webshop.model;

public class GetOrderPositionResponse {
    private String id;
    private ProductResponse product;
    private long quantity;


    public GetOrderPositionResponse(String id, ProductResponse product, long quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public GetOrderPositionResponse() {

    }

    public String getId() {
        return id;
    }

    public ProductResponse getProduct() {
        return product;
    }

    public long getQuantity() {
        return quantity;
    }


}
