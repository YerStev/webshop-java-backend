package webservicees.webshop.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import webservicees.webshop.entity.OrderEntity;
import webservicees.webshop.entity.ProductEntity;
import webservicees.webshop.exception.IdNotFoundException;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.ShoppingCartResponse;
import webservicees.webshop.repository.IOrderRepository;
import webservicees.webshop.repository.IOrderPositionRepository;
import webservicees.webshop.repository.IProductRepository;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ShoppingCartService {
    private IOrderRepository orderRepository;
    private IOrderPositionRepository orderPositionRepository;
    private IProductRepository productRepository;

    public ShoppingCartService(IOrderRepository orderRepository, IOrderPositionRepository orderPositionRepository, IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.productRepository = productRepository;
    }

    public ShoppingCartResponse getShoppingCartForCustomer(String customerId) {
       List<OrderEntity> orders = orderRepository.findAllByCustomerIdWhereOrderStatusIsNew(customerId);
       List<String> orderIds = orders.stream().
               map(o->o.getId()).
               collect(Collectors.toList());

       List<OrderPositionResponse> orderPositions = orderPositionRepository.
               findAllById(orderIds)
               .stream()
               .map(o -> OrderService.mapToOrderPositionResponse(o))
               .collect(Collectors.toList());

        long deliveryCost = 800L; // TODO: feature to select delivery method?
        long totalAmount = calculateSumForCart(orderPositions, deliveryCost);
        return new ShoppingCartResponse(customerId, orderPositions, totalAmount, deliveryCost, "STANDARD" );
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
