package dev.oscarrojas.order_manager.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

public class OrderTest {

    @Test
    void refund_amountExceedingPaymentTotal_throwsException() {
        Product product = new Product("sku", "shirt", "", 5, new HashMap<>());
        Order order = new Order();
        order.setItems(List.of(new OrderItem(product, 1, 1.0)));
        order.setPayments(List.of(new Payment("id", 0.5)));

        assertThrows(Exception.class, () -> {
            order.refund(0.6, PaymentMethod.OTHER, null);
        });
    }

    @Test
    void pay_amountExceedingBalance_throwsException() throws OverPaymentException {
        Product product = new Product("sku", "shirt", "", 5, new HashMap<>());
        Order order = new Order();
        order.setItems(List.of(new OrderItem(product, 1, 1.0)));

        assertThrows(Exception.class, () -> {
            order.pay(1.1, PaymentMethod.OTHER, null);
        });

        order.pay(0.9, PaymentMethod.OTHER, null);

        assertThrows(Exception.class, () -> {
            order.pay(0.11, PaymentMethod.OTHER, null);
        });
    }
}
