package dev.oscarrojas.order_manager.web.forms;

public record CreateOrderLineForm(String variantId, int quantity, double price) {}
