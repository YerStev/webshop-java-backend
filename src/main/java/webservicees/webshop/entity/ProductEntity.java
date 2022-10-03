package webservicees.webshop.entity;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    private String id;
    private String name;
    private String description;
    private int priceInCent;
    @ElementCollection
    private List<String> tags;


    public ProductEntity(
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

    public ProductEntity() {
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriceInCent(int priceInCent) {
        this.priceInCent = priceInCent;
    }
}
