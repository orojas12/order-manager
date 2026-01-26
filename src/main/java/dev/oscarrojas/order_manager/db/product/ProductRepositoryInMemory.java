package dev.oscarrojas.order_manager.db.product;

import dev.oscarrojas.order_manager.product.Product;
import dev.oscarrojas.order_manager.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryInMemory implements ProductRepository {
    @Override
    public Optional<Product> get(String productId) {
        return Optional.empty();
    }

    @Override
    public void create(Product product) {}
}
