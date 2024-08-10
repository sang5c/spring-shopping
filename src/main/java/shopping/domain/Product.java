package shopping.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
public class Product {

    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_PRICE = 0;
    private static final int MAX_DESCRIPTION_LENGTH = 100;
    private static final Pattern URL_PATTERN = Pattern.compile("^https:\\/\\/[a-zA-Z0-9\\-]+\\.com\\/[a-zA-Z0-9_\\-]+\\.(jpg|jpeg|png|gif)$");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    private String imageUrl;

    private String description;

    protected Product() {
    }

    private Product(String name, BigDecimal price, String imageUrl, String description) {
        validate(name, price, imageUrl, description);
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public static Product create(String name, BigDecimal price, String imageUrl, String description) {
        return new Product(name, price, imageUrl, description);
    }

    private void validate(String name, BigDecimal price, String imageUrl, String description) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("상품 이름은 2자 이상 50자 이하여야 합니다.");
        }

        if (price.compareTo(BigDecimal.ZERO) <= MIN_PRICE) {
            throw new IllegalArgumentException("상품 가격은 0보다 커야 합니다.");
        }

        validateImageUrl(imageUrl);

        if (description != null && description.length() > MAX_DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("상품 설명은 100자 이하여야 합니다.");
        }
    }

    private static void validateImageUrl(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("상품 이미지 URL은 100자 이하여야 합니다.");
        }

        Matcher matcher = URL_PATTERN.matcher(imageUrl);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("상품 이미지 URL 형식이 올바르지 않습니다.");
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
