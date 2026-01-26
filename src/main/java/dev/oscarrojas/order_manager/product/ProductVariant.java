package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record ProductVariant(String id, String sku, Product product, Map<String, String> attributes) {}
