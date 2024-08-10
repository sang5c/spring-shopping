package shopping.application;

import shopping.domain.Product;

import java.math.BigDecimal;

public record ProductRegisterRequest(
        String name,
        BigDecimal price,
        String description
) {
    public Product toEntity() {
        return Product.create(name, price, description);
    }
}
