package dev.oscarrojas.order_manager.db.product;

import dev.oscarrojas.order_manager.product.ProductModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository {

    Optional<ProductModel> get(String productId);

    void create(ProductModel product);
}
