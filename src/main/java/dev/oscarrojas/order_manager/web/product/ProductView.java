package dev.oscarrojas.order_manager.web.product;

import java.util.Collection;

public record ProductView(String id, String brand, String name, Collection<ProductVariantView> variants) {}
