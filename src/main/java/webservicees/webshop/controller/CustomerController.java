package webservicees.webshop.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import webservicees.webshop.entity.CustomerEntity;
import webservicees.webshop.exception.WebshopException;
import webservicees.webshop.model.CustomerResponse;
import webservicees.webshop.model.ShoppingCartResponse;
import webservicees.webshop.repository.ICustomerRepository;
import webservicees.webshop.service.ShoppingCartService;

import java.util.Optional;

@RestController
public class CustomerController {
    private ICustomerRepository customerRepository;
    private ShoppingCartService shoppinCartService;

    public CustomerController(ICustomerRepository customerRepository, ShoppingCartService shoppinCartService) {
        this.customerRepository = customerRepository;
        this.shoppinCartService = shoppinCartService;
    }
    @GetMapping("/customers/{id}")
    public CustomerResponse getCustomerById(@PathVariable String id){
        Optional<CustomerEntity> customer = customerRepository.findById(id);
        if(customer.isEmpty())
            throw new WebshopException("Customer with " + id + " id not found", HttpStatus.BAD_REQUEST);
        return new CustomerResponse(
                customer.get().getId(),
                customer.get().getFirstName(),
                customer.get().getLastName(),
                customer.get().getEmail());
    }

    @GetMapping("/customers/{id}/shoppingcart")
    public ShoppingCartResponse getShoppingCartByCustomerId(@PathVariable String id){
        return shoppinCartService.getShoppingCartForCustomer(id);
    }
}
