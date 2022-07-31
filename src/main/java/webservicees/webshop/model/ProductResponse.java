package webservicees.webshop.model;

import java.util.List;

public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private int priceInCent;
    private List<String> tags;

    public String getId() {
        return id;
    }

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

    public ProductResponse(
            String id,
            String name,
            String description,
            int priceInCent,
            List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceInCent = priceInCent;
        this.tags = tags;
    }
}
