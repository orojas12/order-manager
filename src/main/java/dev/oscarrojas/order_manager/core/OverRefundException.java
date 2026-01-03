package dev.oscarrojas.order_manager.core;

public class OverRefundException extends Exception {

    public OverRefundException(String message) {
        super(message);
    }
}
