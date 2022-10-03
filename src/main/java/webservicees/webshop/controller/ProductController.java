package webservicees.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservicees.webshop.entity.ProductEntity;
import webservicees.webshop.exception.IdNotFoundException;
import webservicees.webshop.exception.WebshopException;
import webservicees.webshop.model.ProductCreateRequest;
import webservicees.webshop.model.ProductResponse;
import webservicees.webshop.model.ProductUpdateRequest;
import webservicees.webshop.repository.IProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {
    private final IProductRepository productRepository;

    public ProductController(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String tag) {
        return productRepository.findAll()
                .stream()
                .filter(p -> tag == null || p.getTags().contains(tag))
                .map(p ->mapToResponse(p))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductResponse getProductById(@PathVariable String id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty())
            throw new WebshopException("Product with " + id + " not found", HttpStatus.BAD_REQUEST);
        return mapToResponse(product.get());
    }

    private ProductResponse mapToResponse(ProductEntity product) {
        return new ProductResponse(product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPriceInCent(),
                product.getTags());
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest request) {
        ProductEntity response = new ProductEntity(UUID.randomUUID().toString(),
                request.getName(),
                request.getDescription(),
                request.getPriceInCent(),
                request.getTags());
        ProductEntity saved = productRepository.save(response);
        return mapToResponse(saved);
    }

    @PutMapping("/products/{id}")
    public ProductResponse updateProduct(@RequestBody ProductUpdateRequest request, @PathVariable String id) {
        //mit Immutabillity programmiert
        final ProductEntity product = productRepository.findById(id).
                orElseThrow(() -> new IdNotFoundException("Product with id " + id + " not found", HttpStatus.BAD_REQUEST));
        final ProductEntity updated = new ProductEntity(
                product.getId(),
                request.getName() == null ? product.getName() : request.getName(),
                request.getDescription() == null ? product.getDescription() : request.getDescription(),
                request.getPriceInCent() == null ? product.getPriceInCent() : request.getPriceInCent(),
                product.getTags()
        );
        ProductEntity saved = productRepository.save(updated);
        return mapToResponse(saved);
    }


}
