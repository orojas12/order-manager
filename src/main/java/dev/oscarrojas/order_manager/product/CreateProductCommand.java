package dev.oscarrojas.order_manager.product;

import java.util.List;

public record CreateProductCommand(
        String name, String brand, String description, List<CreateProductVariantCommand> variants) {}
