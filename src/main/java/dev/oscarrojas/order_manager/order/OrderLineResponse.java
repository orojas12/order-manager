package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.product.ProductVariantResponse;

public record OrderLineResponse(ProductVariantResponse variant, int quantity, long unitPrice) {}
