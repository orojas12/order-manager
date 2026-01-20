package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record ProductVariantResponse(String id, String sku, ProductResponse product, Map<String, String> attributes) {}
