package webservicees.webshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import webservicees.webshop.entity.OrderPositionEntity;

@Service
public interface IOrderPositionRepository extends JpaRepository<OrderPositionEntity, String> {
/*
    List<OrderPositionResponse> orderPositions = new ArrayList<>();

    public void save(OrderPositionResponse orderPositionResponse) {
        orderPositions.add(orderPositionResponse);
    }

    public List<OrderPositionResponse> findAllByOrderIds(List<String> orderIds) {
        return orderPositions.stream()
                .filter(o -> orderIds.contains(o.getOrderId()))
                .collect(Collectors.toList());
    }*/


}
