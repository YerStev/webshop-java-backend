package webservicees.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webservicees.webshop.entity.CustomerEntity;

public interface ICustomerRepository extends JpaRepository<CustomerEntity, String> {
}

