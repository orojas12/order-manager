package dev.oscarrojas.order_manager.db.product;

import dev.oscarrojas.order_manager.product.ProductVariantModel;
import dev.oscarrojas.order_manager.product.ProductVariantRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProductVariantRepositoryInMemory implements ProductVariantRepository {

    @Override
    public Optional<ProductVariantModel> get(String variantId) {
        return Optional.empty();
    }

    @Override
    public void create(ProductVariantModel variant) {}
}
