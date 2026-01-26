package dev.oscarrojas.order_manager.customer;

import dev.oscarrojas.order_manager.exception.InvalidRequestException;

public class CreateCustomerValidator {

    public static InvalidRequestException validate(CreateCustomerRequest request) {
        if (request.name() == null) {
            return new InvalidRequestException("Customer name cannot be null");
        }

        if (request.address() != null) {
            if (request.address().street() == null
                    || request.address().city() == null
                    || request.address().postalCode() == null
                    // address.state is optional
                    || request.address().country() == null) {
                return new InvalidRequestException("Required address fields cannot be null if address is present");
            }
        }

        return null;
    }
}
