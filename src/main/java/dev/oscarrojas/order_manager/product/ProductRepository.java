package dev.oscarrojas.order_manager.product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> getById(String productId);

    Optional<ProductVariant> getVariantById(String id);

    void create(Product product);
}
