package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.product.ProductVariantResponse;

public record OrderItemResponse(ProductVariantResponse variant, int quantity, long unitPrice) {}
