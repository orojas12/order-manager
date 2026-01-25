package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateOrderRequestValidatorTest {

    @Test
    void validate_minimumOrderItems() {

        CreateOrderRequestValidator validator = new CreateOrderRequestValidator();

        CreateOrderRequest request =
                new CreateOrderRequest(List.of(), "1", new Address("100 1st St", "New York City", "NY", "10001", "US"));

        assertNotNull(validator.validate(request));
    }

    @Test
    void validate_orderItemMinimumQuantity() {

        CreateOrderRequestValidator validator = new CreateOrderRequestValidator();

        CreateOrderRequest request1 = new CreateOrderRequest(
                List.of(new CreateOrderItem("1", 0, 1)),
                "1",
                new Address("100 1st St", "New York City", "NY", "10001", "US"));

        assertNotNull(validator.validate(request1));

        CreateOrderRequest request2 = new CreateOrderRequest(
                List.of(new CreateOrderItem("1", 0, 1)),
                "1",
                new Address("100 1st St", "New York City", "NY", "10001", "US"));

        assertNotNull(validator.validate(request2));
    }

    @Test
    void validate_orderItemMinimumUnitPrice() {

        CreateOrderRequestValidator validator = new CreateOrderRequestValidator();

        CreateOrderRequest request1 = new CreateOrderRequest(
                List.of(new CreateOrderItem("1", 1, -1)),
                "1",
                new Address("100 1st St", "New York City", "NY", "10001", "US"));

        assertNotNull(validator.validate(request1));

        CreateOrderRequest request2 = new CreateOrderRequest(
                List.of(new CreateOrderItem("1", 1, 0)),
                "1",
                new Address("100 1st St", "New York City", "NY", "10001", "US"));

        assertNull(validator.validate(request2));
    }
}
