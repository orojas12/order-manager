package dev.oscarrojas.order_manager.product;

import java.util.List;

public record Product(String id, String brand, String name, String description, List<ProductVariant> variants) {

    public Product {
        variants = List.copyOf(variants);
    }
}
