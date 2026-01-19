package dev.oscarrojas.order_manager.order;

public record OrderLineResponse(String sku, String description, int quantity, long unitPrice) {}
