package dev.oscarrojas.order_manager.product;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ProductVariantMapper {

    public ProductVariantResponse toResponse(ProductVariant variant) {
        return new ProductVariantResponse(variant.id(), variant.sku(), variant.attributes(), variant.productId());
    }

    public List<ProductVariantResponse> toResponse(Collection<ProductVariant> variants) {
        return variants.stream().map(this::toResponse).toList();
    }
}
