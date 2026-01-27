package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record ProductVariant(String id, String sku, Map<String, String> attributes, String productId) {

    public ProductVariant {
        attributes = Map.copyOf(attributes);
    }
}
