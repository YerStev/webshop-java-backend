package webservicees.webshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import webservicees.webshop.exceptions.IdNotFoundException;
import webservicees.webshop.model.OrderPositionResponse;
import webservicees.webshop.model.ProductResponse;
import webservicees.webshop.repository.OrderPositionRepository;
import webservicees.webshop.repository.OrderRepository;
import webservicees.webshop.repository.ProductRepository;
import webservicees.webshop.service.ShoppingCartService;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ShoppingCartServiceTest {
    private ShoppingCartService service;
    private ProductRepository productRepository;

    @BeforeEach
    public void setupTest(){
        productRepository = mock(ProductRepository.class);
        service = new ShoppingCartService(
                mock(OrderRepository.class),
                mock(OrderPositionRepository.class),
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
        ProductResponse savedProduct = new ProductResponse(UUID.randomUUID().toString(), "", "", 1000, new ArrayList<>());

        // wenn mock findById aufruft, wird savedProduct zurückgegeben, ist also hardgecoded, das Verhalten des mocks wird definiert
        given(productRepository.findById(savedProduct.getId())).willReturn(Optional.of(savedProduct));

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

    @Test
    public void calculateSumWithTwoProductSumPriceOfProducts (){
        // given
        ProductResponse savedProduct1 = new ProductResponse(UUID.randomUUID().toString(), "", "", 1000, new ArrayList<>());
        ProductResponse savedProduct2 = new ProductResponse(UUID.randomUUID().toString(), "", "", 2000, new ArrayList<>());

        given(productRepository.findById(savedProduct1.getId())).willReturn(Optional.of(savedProduct1));
        given(productRepository.findById(savedProduct2.getId())).willReturn(Optional.of(savedProduct2));

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
        ProductResponse savedProduct1 = new ProductResponse(UUID.randomUUID().toString(), "", "", 1000, new ArrayList<>());
        ProductResponse savedProduct2 = new ProductResponse(UUID.randomUUID().toString(), "", "", 2000, new ArrayList<>());


        given(productRepository.findById(savedProduct1.getId())).willReturn(Optional.of(savedProduct1));
        given(productRepository.findById(savedProduct2.getId())).willReturn(Optional.of(savedProduct2));

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
