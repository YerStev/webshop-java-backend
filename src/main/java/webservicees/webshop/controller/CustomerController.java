package webservicees.webshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webservicees.webshop.model.CustomerResponse;
import webservicees.webshop.model.ShoppingCartResponse;
import webservicees.webshop.repository.CustomerRepository;
import webservicees.webshop.service.ShoppingCartService;

import java.util.ArrayList;

@RestController
public class CustomerController {
    private CustomerRepository customerRepository;
    private ShoppingCartService shoppinCartService;

    public CustomerController(CustomerRepository customerRepository, ShoppingCartService shoppinCartService) {
        this.customerRepository = customerRepository;
        this.shoppinCartService = shoppinCartService;
    }
    @GetMapping("/customers/{id}")
    public CustomerResponse getCustomerById(@PathVariable String id){
        return customerRepository.findById(id);
    }

    @GetMapping("/customers/{id}/shoppingcart")
    public ShoppingCartResponse getShoppingCartByCustomerId(@PathVariable String id){
        return shoppinCartService.getShoppingCartForCustomer(id);
    }
}
