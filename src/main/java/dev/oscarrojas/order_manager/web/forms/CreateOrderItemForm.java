package dev.oscarrojas.order_manager.web.forms;

public record CreateOrderItemForm(String variantId, int quantity, double price) {}
