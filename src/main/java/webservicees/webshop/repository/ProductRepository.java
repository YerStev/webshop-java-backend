package webservicees.webshop.repository;

import webservicees.webshop.model.ProductResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        String tagLowerCase = tag.toLowerCase();
        return products.stream()
                .filter(p -> lowerCaseTags(p).contains(tagLowerCase))
                .collect(Collectors.toList());
    }

    private static List<String> lowerCaseTags(ProductResponse p) {
        return p.getTags().stream()
                .map(t -> t.toLowerCase())
                .collect(Collectors.toList());
    }
}
