package webservicees.webshop.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webservicees.webshop.exceptions.IdNotFoundException;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.OrderResponse;
import webservicees.webshop.model.ProductResponse;
import webservicees.webshop.model.ShoppingCartResponse;
import webservicees.webshop.repository.OrderPositionRepository;
import webservicees.webshop.repository.OrderRepository;
import webservicees.webshop.repository.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingCartService {
    private OrderRepository orderRepository;
    private OrderPositionRepository orderPositionRepository;
    private ProductRepository productRepository;

    public ShoppingCartService(OrderRepository orderRepository, OrderPositionRepository orderPositionRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.productRepository = productRepository;
    }

    public ShoppingCartResponse getShoppingCartForCustomer(String customerId) {
       List<OrderResponse> orders = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId);
       List<String> orderIds = orders.stream().
               map(o->o.getId()).
               collect(Collectors.toList());

       List<OrderPositionResponse> orderPositions = orderPositionRepository.findAllByOrderIds(orderIds);
        long deliveryCost = 800L; // TODO: feature to select delivery method?
        long totalAmount = calculateSumForCart(orderPositions, deliveryCost);
        return new ShoppingCartResponse(customerId, orderPositions, totalAmount, deliveryCost, "STANDARD" );
    }
    public long calculateSumForCart(List <OrderPositionResponse> orderPositions, long deliveryCost){
        List<Long> positionAmounts = orderPositions.stream()
                .map(o -> {
                            ProductResponse product = productRepository.findById(o.getProductId())
                                    .orElseThrow(() -> new IdNotFoundException("Product with id " + o.getProductId() + "not found", HttpStatus.BAD_REQUEST));
                            if(o.getQuantity() <= 0)
                                throw new IllegalArgumentException("OrderPosition with quantity of " + o.getQuantity() + " is not allowed");
                            return o.getQuantity() * product.getPriceInCent();
                        }
                ).collect(Collectors.toList());

        long positionSum = 0L;
        for(long p : positionAmounts) positionSum += p;
        return positionSum + deliveryCost;
    }
}
