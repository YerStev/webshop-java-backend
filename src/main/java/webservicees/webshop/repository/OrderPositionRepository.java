package webservicees.webshop.repository;

import webservicees.webshop.model.OrderPositionResponse;

import java.util.ArrayList;
import java.util.List;

public class OrderPositionRepository {

    List<OrderPositionResponse> orderPositions = new ArrayList<>();

    public void save(OrderPositionResponse orderPositionResponse) {
        orderPositions.add(orderPositionResponse);
    }
}
