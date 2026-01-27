package dev.oscarrojas.order_manager.db.product;

import dev.oscarrojas.order_manager.product.Product;
import dev.oscarrojas.order_manager.product.ProductRepository;
import dev.oscarrojas.order_manager.product.ProductVariant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductRepositoryInMemory implements ProductRepository {
    @Override
    public Optional<Product> getById(String productId) {
        return Optional.empty();
    }

    @Override
    public Optional<ProductVariant> getVariantById(String id) {
        return Optional.empty();
    }

    @Override
    public void create(Product product) {}
}
