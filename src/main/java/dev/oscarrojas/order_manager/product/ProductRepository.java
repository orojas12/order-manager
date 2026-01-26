package dev.oscarrojas.order_manager.product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> get(String productId);

    void create(Product product);
}
