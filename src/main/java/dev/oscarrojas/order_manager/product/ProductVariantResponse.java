package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record ProductVariantResponse(String id, String sku, Map<String, String> attributes, String productId) {}
