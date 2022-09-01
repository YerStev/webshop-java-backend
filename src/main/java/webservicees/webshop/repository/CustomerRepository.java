package webservicees.webshop.repository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webservicees.webshop.exceptions.IdNotFoundException;
import webservicees.webshop.model.CustomerResponse;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerRepository {

    List<CustomerResponse> customers = Arrays.asList(
            new CustomerResponse(
                    "1",
                    "Lou",
                    "Jeff",
                    "lou.jeff@gmail.de")
    );

    //Optinal, weil return auch null sein kann
    public CustomerResponse findById(String id) {
        Optional<CustomerResponse> first = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        if(first.isEmpty())
        throw new IdNotFoundException("Customer with id " + id + " not found", HttpStatus.BAD_REQUEST);
        return first.get();
    }
}
