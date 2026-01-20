package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.product.ProductVariantModel;

public record OrderLineModel(ProductVariantModel variant, int quantity, long unitPrice) {}
