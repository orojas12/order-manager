package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.exception.InvalidRequestException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderRequestValidatorTest {

    @Test
    void validate_zeroLines_returnsException() {

        CreateOrderRequest request =
                new CreateOrderRequest(List.of(), "1", new Address("100 1st St", "New York City", "NY", "10001", "US"));

        CreateOrderRequestValidator validator = new CreateOrderRequestValidator();

        assertNotNull(validator.validate(request));
    }

    @Test
    void validate_lessThanOneQuantity_returnsException() {

        CreateOrderRequest request = new CreateOrderRequest(
                List.of(new CreateOrderLine("1", 0, 1)),
                "1",
                new Address("100 1st St", "New York City", "NY", "10001", "US"));

        CreateOrderRequestValidator validator = new CreateOrderRequestValidator();

        assertNotNull(validator.validate(request));
    }
}
