package webservicees.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservicees.webshop.entity.ProductEntity;

public interface IProductRepository extends JpaRepository<ProductEntity, String> {

/*    private List<ProductResponse> products = new ArrayList<>();

    public ProductRepository() {
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "Hemd",
                        "sau gute beschreibung",
                        1234,
                        Arrays.asList("kleidung", "oversize")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "hose",
                        "sau gute beschreibung",
                        1234,
                        Arrays.asList("kleidung", "regular")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "tshirt",
                        "sau gute beschreibung",
                        1234,
                        Arrays.asList("kleidung", "oversize")
                ));
        products.add(
                new ProductResponse(
                        UUID.randomUUID().toString(),
                        "shorts",
                        "sau gute beschreibung",
                        1234,
                        Arrays.asList("kleidung", "slim")
                ));
    }

    public List<ProductResponse> getAll(String tag) {
        if (tag == null) return products;

        String tagLowerCase = tag.toLowerCase();
        return products.stream()
                .filter(p -> lowerCaseTags(p).contains(tagLowerCase))
                .collect(Collectors.toList());
    }

    private List<String> lowerCaseTags(ProductResponse p) {
        return p.getTags().stream()
                .map(t -> t.toLowerCase())
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> findById(String id){
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public void deleteById(String id){
      products =  products.stream()
                .filter(p -> !p.getId().equals(id))
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductResponse response) {
        products.add(response);
        return response;
    }*/


}
