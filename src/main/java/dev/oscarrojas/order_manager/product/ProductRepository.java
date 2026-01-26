package dev.oscarrojas.order_manager.product;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository {

    Optional<Product> get(String productId);

    void create(Product product);
}
