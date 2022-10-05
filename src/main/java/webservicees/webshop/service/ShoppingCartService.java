package webservicees.webshop.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webservicees.webshop.entity.OrderEntity;
import webservicees.webshop.entity.OrderPositionEntity;
import webservicees.webshop.entity.ProductEntity;
import webservicees.webshop.exception.IdNotFoundException;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.ShoppingCartResponse;
import webservicees.webshop.repository.IOrderRepository;
import webservicees.webshop.repository.IProductRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingCartService {
    private IOrderRepository orderRepository;
    private IProductRepository productRepository;

    public ShoppingCartService(IOrderRepository orderRepository, IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public ShoppingCartResponse getShoppingCartForCustomer(String customerId) {
       List<OrderEntity> orders = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId);

       List<OrderPositionEntity> orderPositionEntities = orders
               .stream()
               .flatMap(o -> o.getOrderPositions().stream())
               .collect(Collectors.toList());

       List<OrderPositionResponse> orderPositionResponses = orderPositionEntities
               .stream()
               .map(o -> OrderService.mapToOrderPositionResponse(o))
               .collect(Collectors.toList());

        long deliveryCost = 800L; // TODO: feature to select delivery method?
        long totalAmount = calculateSumForCart(orderPositionResponses, deliveryCost);
        return new ShoppingCartResponse(customerId, orderPositionResponses, totalAmount, deliveryCost, "STANDARD" );
    }
    public long calculateSumForCart(List <OrderPositionResponse> orderPositions, long deliveryCost){
        List<Long> positionAmounts = orderPositions.stream()
                .map(o -> {
                            ProductEntity product = productRepository.findById(o.getProductId())
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
