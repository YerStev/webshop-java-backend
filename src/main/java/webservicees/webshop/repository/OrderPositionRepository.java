package webservicees.webshop.repository;

import org.springframework.stereotype.Service;
import webservicees.webshop.model.OrderPositionResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderPositionRepository {

    List<OrderPositionResponse> orderPositions = new ArrayList<>();

    public void save(OrderPositionResponse orderPositionResponse) {
        orderPositions.add(orderPositionResponse);
    }

    public List<OrderPositionResponse> findAllByOrderIds(List<String> orderIds) {
        return orderPositions.stream()
                .filter(o -> orderIds.contains(o.getOrderId()))
                .collect(Collectors.toList());
    }
}
