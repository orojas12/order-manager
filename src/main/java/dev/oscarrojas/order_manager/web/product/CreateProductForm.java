package dev.oscarrojas.order_manager.web.product;

import java.util.Collection;

public record CreateProductForm(
        String name, String brand, String description, Collection<CreateVariantForm> variants) {}
