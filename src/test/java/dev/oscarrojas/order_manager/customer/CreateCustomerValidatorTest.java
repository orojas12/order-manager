package dev.oscarrojas.order_manager.customer;

import dev.oscarrojas.order_manager.core.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCustomerValidatorTest {

    @Test
    void validate_requiredFieldsAreNotNull() {
        CreateCustomerRequest request = new CreateCustomerRequest(null, null, null, null);

        assertNotNull(CreateCustomerValidator.validate(request));
    }

    @Test
    void validate_optionalFieldsCanBeNull() {
        CreateCustomerRequest request = new CreateCustomerRequest("name", null, null, null);

        assertNull(CreateCustomerValidator.validate(request));
    }

    @Test
    void validate_addressFieldsAreNotNullIfAddressIsPresent() {
        CreateCustomerRequest request =
                new CreateCustomerRequest("name", null, null, new Address(null, null, null, null, null));

        assertNotNull(CreateCustomerValidator.validate(request));
    }
}
