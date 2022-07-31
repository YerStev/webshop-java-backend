package webservicees.webshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import webservicees.webshop.model.ProductCreateRequest;
import webservicees.webshop.model.ProductResponse;
import webservicees.webshop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    ProductRepository productRepository = new ProductRepository();

    @GetMapping("/products")
    public List<ProductResponse> getAllProducts(@RequestParam(required = false) String tag) {
        return productRepository.getAll(tag);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable String id){
        Optional<ProductResponse> product = productRepository.findById(id);
        if(product.isPresent())
            return ResponseEntity.ok(product.get());
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity <String> deleteProduct(@PathVariable String id){
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody ProductCreateRequest request){
        return productRepository.save(request);
    }

}
