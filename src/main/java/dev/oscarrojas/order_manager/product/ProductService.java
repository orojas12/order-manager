package dev.oscarrojas.order_manager.product;

import dev.oscarrojas.order_manager.exception.ValidationException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CreateProductValidator validator;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        this.validator = new CreateProductValidator();
    }

    public ProductResponse createProduct(CreateProductCommand cmd) {

        List<String> errors = validator.validate(cmd);

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        List<ProductVariant> variants = cmd.variants().stream()
                .map(variant -> new ProductVariant(
                        UUID.randomUUID().toString(),
                        variant.sku(),
                        variant.price(),
                        variant.stockQuantity(),
                        variant.attributes()))
                .toList();

        Product product =
                new Product(UUID.randomUUID().toString(), cmd.brand(), cmd.name(), cmd.description(), variants);

        repository.create(product);

        return new ProductResponse(product.id(), product.brand(), product.name(), product.description());
    }
}
