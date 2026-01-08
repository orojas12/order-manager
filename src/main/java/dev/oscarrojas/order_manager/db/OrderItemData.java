package dev.oscarrojas.order_manager.db;

public record OrderItemData(ProductData product, int quantity, long unitPrice) {}
