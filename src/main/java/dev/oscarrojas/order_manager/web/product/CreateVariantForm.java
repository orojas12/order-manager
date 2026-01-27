package dev.oscarrojas.order_manager.web.product;

import java.util.Map;

public record CreateVariantForm(String sku, Map<String, String> attributes) {}
