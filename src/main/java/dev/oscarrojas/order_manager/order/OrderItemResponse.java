package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.product.ProductResponse;
import dev.oscarrojas.order_manager.product.ProductVariantResponse;

public record OrderItemResponse(
        ProductResponse product, ProductVariantResponse variant, int quantity, long unitPrice) {}
