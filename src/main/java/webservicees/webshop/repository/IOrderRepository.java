package webservicees.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import webservicees.webshop.entity.OrderEntity;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, String>{
    //Methode fuehrt sql aus, bzw eine Abwandlung davon -> jpql

    @Query("SELECT e FROM OrderEntity e WHERE e.status = 'NEW' AND e.customerId = :customerId")
    List<OrderEntity> findAllByCustomerIdWhereOrderStatusIsNew(String customerId);
}
