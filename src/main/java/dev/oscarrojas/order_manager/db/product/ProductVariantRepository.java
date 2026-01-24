package dev.oscarrojas.order_manager.db.product;

import dev.oscarrojas.order_manager.product.ProductVariantModel;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariantRepository {

    Optional<ProductVariantModel> get(String variantId);

    void create(ProductVariantModel variant);
}
