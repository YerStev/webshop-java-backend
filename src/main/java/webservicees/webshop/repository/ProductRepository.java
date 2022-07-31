package webservicees.webshop.repository;

import webservicees.webshop.model.ProductResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductRepository {

    List<ProductResponse> products = Arrays.asList(
            new ProductResponse(
                    "1",
                    "Hemd",
                    "sau gute beschreibung",
                    1234,
                    Arrays.asList("kleidung", "oversize")
            ),
            new ProductResponse(
                    "2",
                    "hose",
                    "sau gute beschreibung",
                    1234,
                    Arrays.asList("kleidung", "regular")
            ),
            new ProductResponse(
                    "3",
                    "tshirt",
                    "sau gute beschreibung",
                    1234,
                    Arrays.asList("kleidung", "oversize")
            ),
            new ProductResponse(
                    "4",
                    "shorts",
                    "sau gute beschreibung",
                    1234,
                    Arrays.asList("kleidung", "slim")
            )
    );

    public List<ProductResponse> getAll(String tag) {


        if (tag == null) return products;
        tag = tag.toLowerCase();
        List<ProductResponse> filtered = new ArrayList<>();

        for (ProductResponse p : products) {
            if (lowerCaseTags(p).contains(tag))
                filtered.add(p);
        }
        return filtered;
    }

    private static List<String> lowerCaseTags(ProductResponse p) {
        List<String> lowerCaseTags = new ArrayList<>();
        for (String t : p.getTags())
            lowerCaseTags.add(t.toLowerCase());
        return lowerCaseTags;
    }
}
