package dev.oscarrojas.order_manager.order;

public record CreateOrderLine(String variantId, int quantity, long unitPrice) {}
