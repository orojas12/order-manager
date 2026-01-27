package dev.oscarrojas.order_manager.product;

import dev.oscarrojas.order_manager.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class CreateProductValidator implements Validator<CreateProductCommand> {

    public List<String> validate(CreateProductCommand cmd) {
        List<String> errors = new ArrayList<>();

        if (cmd.name() == null || cmd.name().isBlank()) {
            errors.add("Name cannot be blank");
        }

        if (cmd.variants() == null || cmd.variants().size() < 1) {
            errors.add("Products must contain at least one variant");
        }

        return errors;
    }
}
