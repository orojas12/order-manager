package dev.oscarrojas.order_manager.product;

import java.util.Collection;
import java.util.List;

public class ProductVariantMapper {

    public ProductVariantResponse toResponse(ProductVariant variant) {
        return new ProductVariantResponse(variant.id(), variant.sku(), variant.attributes());
    }

    public List<ProductVariantResponse> toResponse(Collection<ProductVariant> variants) {
        return variants.stream().map(this::toResponse).toList();
    }
}
