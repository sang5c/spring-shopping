package shopping.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopping.domain.Product;
import shopping.domain.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductRegisterResponse registerProduct(ProductRegisterRequest request) {
        Product product = productRepository.save(request.toEntity());
        return ProductRegisterResponse.from(product);
    }
}
