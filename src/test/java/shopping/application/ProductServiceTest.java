package shopping.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shopping.fakeobject.ProductInMemoryRepository;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setup() {
        productService = new ProductService(new ProductInMemoryRepository());
    }

    @Test
    void registerProduct() {
        ProductRegisterRequest request = new ProductRegisterRequest("Apple", BigDecimal.valueOf(1000), "https://test.com/sample.jpg", "This is an apple");

        ProductRegisterResponse productRegisterResponse = productService.registerProduct(request);

        assertThat(productRegisterResponse.id()).isNotNull();
        assertThat(productRegisterResponse.name()).isEqualTo("Apple");
        assertThat(productRegisterResponse.price()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(productRegisterResponse.description()).isEqualTo("This is an apple");
    }

}
