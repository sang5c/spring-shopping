package shopping.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_PRICE = 0;
    private static final int MAX_DESCRIPTION_LENGTH = 100;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private String description;

    protected Product() {
    }

    private Product(String name, BigDecimal price, String description) {
        validate(name, price, description);
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public static Product create(String name, BigDecimal price, String description) {
        return new Product(name, price, description);
    }

    private void validate(String name, BigDecimal price, String description) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("상품 이름은 2자 이상 50자 이하여야 합니다.");
        }

        if (price.compareTo(BigDecimal.ZERO) <= MIN_PRICE) {
            throw new IllegalArgumentException("상품 가격은 0보다 커야 합니다.");
        }

        if (description != null && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("상품 설명은 100자 이하여야 합니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
