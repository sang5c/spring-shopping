package shopping.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class ProductTest {

    @DisplayName("0원 미만 상품은 예외가 발생한다")
    @Test
    void create() {
        assertThatThrownBy(() -> Product.create("상품", BigDecimal.valueOf(-1), null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품 이름이 2자 미만 50자 초과이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"한", "일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십1"})
    void create2(String name) {
        assertThatThrownBy(() -> Product.create(name, BigDecimal.valueOf(1000), null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("상품 설명이 100자 초과이면 예외가 발생한다")
    @Test
    void create3() {
        assertThatThrownBy(() ->
                Product.create(
                        "상품",
                        BigDecimal.valueOf(1000),
                        "백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백한자백1"
                ))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("설명이 포함되거나, 포함되지 않는 상품을 생성할 수 있다")
    @Test
    void create4() {
        assertThatCode(() -> {
            Product.create("상품", BigDecimal.valueOf(1000), null);
            Product.create("상품", BigDecimal.valueOf(1000), "상품 설명");
        }).doesNotThrowAnyException();
    }
}
