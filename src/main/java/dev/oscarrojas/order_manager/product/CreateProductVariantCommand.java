package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record CreateProductVariantCommand(String sku, long price, int stockQuantity, Map<String, String> attributes) {}
