package dev.oscarrojas.order_manager.product;

import dev.oscarrojas.order_manager.exception.ValidationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;
    private final CreateProductValidator validator;

    public ProductService(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = new CreateProductValidator();
    }

    public ProductResponse createProduct(CreateProductCommand cmd) {

        List<String> errors = validator.validate(cmd);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        String productId = UUID.randomUUID().toString();

        List<ProductVariant> variants = cmd.variants().stream()
                .map(variant -> new ProductVariant(
                        UUID.randomUUID().toString(), variant.sku(), variant.attributes(), productId))
                .toList();

        Product product = new Product(productId, cmd.brand(), cmd.name(), cmd.description(), variants);

        repository.create(product);

        List<ProductVariantResponse> variantResponses = variants.stream()
                .map(variant -> new ProductVariantResponse(
                        variant.id(), variant.sku(), variant.attributes(), variant.productId()))
                .toList();

        return new ProductResponse(
                product.id(), product.brand(), product.name(), product.description(), variantResponses);
    }

    public Collection<ProductResponse> getProductList() {
        return repository.getAll().stream().map(mapper::toResponse).toList();
    }
}
