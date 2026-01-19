package dev.oscarrojas.order_manager.db.order;

import dev.oscarrojas.order_manager.db.ProductVariantModel;

public record OrderLineModel(ProductVariantModel productVariant, int quantity, long unitPrice) {}
