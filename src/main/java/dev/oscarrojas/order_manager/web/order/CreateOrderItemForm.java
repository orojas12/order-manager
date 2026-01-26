package dev.oscarrojas.order_manager.web.order;

public record CreateOrderItemForm(String variantId, int quantity, double price) {}
