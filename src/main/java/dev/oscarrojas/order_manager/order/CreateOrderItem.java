package dev.oscarrojas.order_manager.order;

public record CreateOrderItem(CreateProduct product, int quantity, long unitPrice) {}
