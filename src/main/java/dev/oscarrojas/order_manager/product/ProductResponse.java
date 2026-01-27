package dev.oscarrojas.order_manager.product;

import java.util.Collection;

public record ProductResponse(
        String id, String brand, String name, String description, Collection<ProductVariantResponse> variants) {}
