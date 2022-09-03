package webservicees.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webservicees.webshop.exceptions.IdNotFoundException;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.ProductCreateRequest;
import webservicees.webshop.model.ProductResponse;
import webservicees.webshop.repository.OrderPositionRepository;
import webservicees.webshop.repository.OrderRepository;
import webservicees.webshop.repository.ProductRepository;
import webservicees.webshop.service.ShoppingCartService;
import java.util.ArrayList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShoppingCartServiceTest {
    private ShoppingCartService service;
    private ProductRepository productRepository;

    @BeforeEach
    public void setupTest(){
        productRepository = new ProductRepository();
        service = new ShoppingCartService(
                new OrderRepository(),
                new OrderPositionRepository(),
                productRepository
        );
    }

    @Test
    public void calculateSumForEmptyCartReturnsDeliveryCost (){
        // when
        long result = service.calculateSumForCart(new ArrayList<>(), 500);

        // then
        assertThat(result).isEqualTo(500);
    }

    @Test
    public void calculateSumWithOneProductSumPriceOfProduct (){
       // given
        ProductResponse savedProduct = saveProduct(1000);
        ArrayList<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct, 1);
        // when

        long result = service.calculateSumForCart(orderPositions, 500);
        // then
        assertThat(result).isEqualTo(1500);
    }

    private static void addOrderPosition(ArrayList<OrderPositionResponse> orderPositions, ProductResponse savedProduct,  int quantity) {
        orderPositions.add(new OrderPositionResponse("", savedProduct.getId(), "", quantity));
    }

    private ProductResponse saveProduct(int price) {
        return productRepository.save(new ProductCreateRequest(
                "", "", price, new ArrayList<>()
        ));
    }

    @Test
    public void calculateSumWithTwoProductSumPriceOfProducts (){
        // given
        ProductResponse savedProduct1 = saveProduct(1000);
        ProductResponse savedProduct2 = saveProduct(2000);
        ArrayList<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct1, 1);
        addOrderPosition(orderPositions, savedProduct2, 4);
        // when
        long result = service.calculateSumForCart(orderPositions, 500);
        // then
        assertThat(result).isEqualTo(9500);
    }

    @Test
    public void calculateSumWithNotExistingProductThrowsException (){
        // given
        ProductResponse notSavedProduct = new ProductResponse("", "", "", 100, new ArrayList<>());
        ArrayList<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, notSavedProduct, 1);

        // then
        assertThrows(IdNotFoundException.class, () ->{
            // when
            service.calculateSumForCart(orderPositions, 500);
        });
    }

    @Test
    public void testThatCalculateSumWithNegativeQuantityThrowsException (){
        // given
        ProductResponse savedProduct1 = saveProduct(1000);
        ProductResponse savedProduct2 = saveProduct(2000);
        ArrayList<OrderPositionResponse> orderPositions = new ArrayList<>();
        addOrderPosition(orderPositions, savedProduct1, 1);
        addOrderPosition(orderPositions, savedProduct2, -4);

        // then
        assertThrows(IllegalArgumentException.class, () ->{
            // when
            service.calculateSumForCart(orderPositions, 500);
        });
    }
}
