package dev.oscarrojas.order_manager.orders;

import java.util.Map;

public record CreateProduct(String sku, String name, String desc, Map<String, String> attributes) {}
