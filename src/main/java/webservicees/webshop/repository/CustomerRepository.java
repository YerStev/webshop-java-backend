package webservicees.webshop.repository;

import org.springframework.stereotype.Service;
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
    public Optional <CustomerResponse> findById(String id) {
     return  customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }
}
