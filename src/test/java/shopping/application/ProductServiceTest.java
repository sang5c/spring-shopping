package shopping.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void registerProduct() {
        ProductRegisterRequest request = new ProductRegisterRequest("Apple", BigDecimal.valueOf(1000), "This is an apple");

        ProductRegisterResponse productRegisterResponse = productService.registerProduct(request);

        assertThat(productRegisterResponse.id()).isNotNull();
        assertThat(productRegisterResponse.name()).isEqualTo("Apple");
        assertThat(productRegisterResponse.price()).isEqualTo(BigDecimal.valueOf(1000));
        assertThat(productRegisterResponse.description()).isEqualTo("This is an apple");
    }

}
