package dev.oscarrojas.order_manager.product;

import java.util.Map;

public record CreateVariantCommand(String sku, Map<String, String> attributes) {}
