package dev.oscarrojas.order_manager.product;

import java.util.Collection;

public record CreateProductCommand(
        String name, String brand, String description, Collection<CreateVariantCommand> variants) {}
