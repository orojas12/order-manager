package dev.oscarrojas.order_manager.product;

import java.util.Optional;

public interface ProductVariantRepository {

    Optional<ProductVariantModel> get(String variantId);

    void create(ProductVariantModel variant);
}
