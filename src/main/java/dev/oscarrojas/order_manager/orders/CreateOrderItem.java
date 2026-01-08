package dev.oscarrojas.order_manager.orders;

public record CreateOrderItem(CreateProduct product, int quantity, long unitPrice) {}
