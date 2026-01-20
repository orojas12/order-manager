package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record ProductVariantModel(String id, String sku, ProductModel product, Map<String, String> attributes) {}
