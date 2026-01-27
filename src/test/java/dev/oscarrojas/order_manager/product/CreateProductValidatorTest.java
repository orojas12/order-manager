package dev.oscarrojas.order_manager.product;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductValidatorTest {

    @Test
    void validate_NameNotBlank() {
        CreateProductValidator validator = new CreateProductValidator();

        CreateProductCommand cmd =
                new CreateProductCommand("", "", "", List.of(new CreateVariantCommand("sku", Map.of())));

        List<String> errors = validator.validate(cmd);

        assertEquals(1, errors.size());
    }

    @Test
    void validate_AtLeastOneVariant() {
        CreateProductValidator validator = new CreateProductValidator();

        CreateProductCommand cmd = new CreateProductCommand("name", "", "", List.of());

        List<String> errors = validator.validate(cmd);

        assertEquals(1, errors.size());
    }
}
