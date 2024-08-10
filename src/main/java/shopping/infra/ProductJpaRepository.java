package shopping.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.domain.Product;
import shopping.domain.ProductRepository;

public interface ProductJpaRepository extends ProductRepository, JpaRepository<Product, Long> {
}
