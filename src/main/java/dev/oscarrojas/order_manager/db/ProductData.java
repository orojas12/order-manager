package dev.oscarrojas.order_manager.db;

import java.util.Map;

public record ProductData(String sku, String name, String desc, Map<String, String> attributes, double price) {}
