package dev.oscarrojas.order_manager.order;

public record CreateOrderItem(String variantId, int quantity, long unitPrice) {}
