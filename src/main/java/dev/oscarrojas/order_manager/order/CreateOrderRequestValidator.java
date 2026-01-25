package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.exception.InvalidRequestException;

public class CreateOrderRequestValidator {

    public CreateOrderRequestValidator() {}

    public InvalidRequestException validate(CreateOrderRequest request) {

        if (request.items().isEmpty()) {
            return new InvalidRequestException("An order cannot contain zero order items");
        }

        for (CreateOrderItem line : request.items()) {
            if (line.quantity() < 1) {
                return new InvalidRequestException("Order line quantity cannot be less than 1");
            }

            if (line.unitPrice() < 0) {
                return new InvalidRequestException("Order line unit price cannot be negative");
            }
        }

        return null;
    }
}
