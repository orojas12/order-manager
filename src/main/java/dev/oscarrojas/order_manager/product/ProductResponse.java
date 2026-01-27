package dev.oscarrojas.order_manager.product;

import java.util.List;

public record ProductResponse(
        String id, String brand, String name, String description, List<ProductVariantResponse> variants) {}
