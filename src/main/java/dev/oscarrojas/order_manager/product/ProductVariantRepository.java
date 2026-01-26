package dev.oscarrojas.order_manager.product;

import java.util.Optional;

public interface ProductVariantRepository {

    Optional<ProductVariant> get(String variantId);

    void create(ProductVariant variant);
}
