package shopping.application;

import shopping.domain.Product;

import java.math.BigDecimal;

public record ProductRegisterResponse(
        Long id,
        String name,
        BigDecimal price,
        String description
) {
    public static ProductRegisterResponse from(Product product) {
        return new ProductRegisterResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription()
        );
    }
}
