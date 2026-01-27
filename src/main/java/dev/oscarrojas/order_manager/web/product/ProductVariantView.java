package dev.oscarrojas.order_manager.web.product;

import java.util.Map;

public record ProductVariantView(String id, String sku, Map<String, String> attributes, String productId) {}
