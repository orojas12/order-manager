package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.exception.InvalidRequestException;

public class CreateOrderRequestValidator {

    public CreateOrderRequestValidator() {}

    public InvalidRequestException validate(CreateOrderRequest request) {

        if (request.lines().isEmpty()) {
            return new InvalidRequestException("An order cannot contain zero order lines");
        }

        for (CreateOrderLine line : request.lines()) {
            if (line.quantity() < 1) {
                return new InvalidRequestException("Order line quantity cannot be less than 1");
            }
        }

        return null;
    }
}
