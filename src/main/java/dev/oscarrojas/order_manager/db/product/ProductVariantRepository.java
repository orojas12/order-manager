package dev.oscarrojas.order_manager.db.product;

import dev.oscarrojas.order_manager.product.ProductVariantModel;

import java.util.Optional;

public interface ProductVariantRepository {

    Optional<ProductVariantModel> get(String variantId);

    void create(ProductVariantModel variant);
}
