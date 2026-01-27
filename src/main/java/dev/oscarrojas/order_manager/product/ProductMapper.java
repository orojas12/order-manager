package dev.oscarrojas.order_manager.product;

import java.util.Collection;
import java.util.List;

public class ProductMapper {

    private final ProductVariantMapper variantMapper;

    public ProductMapper(ProductVariantMapper variantMapper) {
        this.variantMapper = variantMapper;
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.id(),
                product.brand(),
                product.name(),
                product.description(),
                variantMapper.toResponse(product.variants()));
    }

    public List<ProductResponse> toResponse(Collection<Product> products) {
        return products.stream().map(this::toResponse).toList();
    }
}
