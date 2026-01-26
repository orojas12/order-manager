package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.product.ProductVariant;

public record OrderItem(ProductVariant variant, int quantity, long unitPrice) {}
