package webservicees.webshop.model;

import java.util.List;

public class ProductCreateRequest {
    private String name;
    private String description;
    private int priceInCent;
    private List<String> tags;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPriceInCent() {
        return priceInCent;
    }

    public List<String> getTags() {
        return tags;
    }

    public ProductCreateRequest(
            String id,
            String name,
            String description,
            int priceInCent,
            List<String> tags) {
        this.name = name;
        this.description = description;
        this.priceInCent = priceInCent;
        this.tags = tags;
    }
    public ProductCreateRequest() {
    }
}
