package dev.oscarrojas.order_manager.web;

public record OrderLineView(String variantId, String name, String quantity, String unitPrice) {}
