package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record ProductVariant(String id, String sku, long price, int stockQuantity, Map<String, String> attributes) {

    public ProductVariant {
        attributes = Map.copyOf(attributes);
    }
}
