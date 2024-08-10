package shopping.fakeobject;

import org.springframework.test.util.ReflectionTestUtils;
import shopping.domain.Product;
import shopping.domain.ProductRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ProductInMemoryRepository implements ProductRepository {

    private final Map<Long, Product> products = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Product save(Product product) {
        long id = idGenerator.getAndIncrement();
        ReflectionTestUtils.setField(product, "id", id);
        products.put(id, product);
        return product;
    }
}
