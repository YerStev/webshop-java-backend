package webservicees.webshop.model;

public class ProductUpdateRequest {
    private String name;
    private String description;
    private Integer priceInCent;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPriceInCent() {
        return priceInCent;
    }

    public ProductUpdateRequest(String name, String description, int priceInCent) {
        this.name = name;
        this.description = description;
        this.priceInCent = priceInCent;
    }
}
