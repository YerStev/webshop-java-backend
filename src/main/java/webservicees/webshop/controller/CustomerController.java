package webservicees.webshop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webservicees.webshop.model.CustomerResponse;
import webservicees.webshop.repository.CustomerRepository;

import java.util.Optional;

@RestController
public class CustomerController {
    CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id){
        Optional<CustomerResponse> customer = customerRepository.findById(id);
        if(customer.isPresent())
            return ResponseEntity.ok(customer.get());
        return ResponseEntity.notFound().build();
    }
}
