package dev.oscarrojas.order_manager.product;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository {

    Collection<Product> getAll();

    Optional<Product> getById(String productId);

    Optional<ProductVariant> getVariantById(String id);

    void create(Product product);
}
