package dev.oscarrojas.order_manager.core;

public class OverPaymentException extends Exception {

    public OverPaymentException(String message) {
        super(message);
    }
}
