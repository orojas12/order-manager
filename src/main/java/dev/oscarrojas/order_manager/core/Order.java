package dev.oscarrojas.order_manager.core;

import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Order {

    private OrderStatus status;
    private List<OrderItem> items;
    private List<Payment> payments;
    private List<Refund> refunds;
    private Customer customer;
    private Shipment shipment;

    public Order() {
        items = new ArrayList<>();
        payments = new ArrayList<>();
        refunds = new ArrayList<>();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getItems() {
        return List.copyOf(items);
    }

    public void setItems(Collection<OrderItem> items) {
        this.items = new ArrayList<>(items);
    }

    public List<Payment> getPayments() {
        return List.copyOf(payments);
    }

    public void setPayments(Collection<Payment> payments) {
        this.payments = new ArrayList<>(payments);
    }

    public List<Refund> getRefunds() {
        return List.copyOf(refunds);
    }

    public void setRefunds(Collection<Refund> refunds) {
        this.refunds = new ArrayList<>(refunds);
    }

    public void pay(double amount, PaymentMethod method, String memo) throws OverPaymentException {

        if (amount > getBalance()) {
            throw new OverPaymentException(
                    String.format("Payment amount of %.2f exceeds remaining order balance", amount));
        }

        payments.add(new Payment(UUID.randomUUID().toString(), amount, method, memo));
    }

    @Nullable
    public Payment deletePayment(String id) {

        for (Payment payment : payments) {
            if (payment.getId().equals(id)) {
                payments.remove(payment);
                return payment;
            }
        }

        return null;
    }

    public void refund(double amount, PaymentMethod method, String memo) throws OverRefundException {
        if (amount > getTotalPayment()) {
            throw new OverRefundException(
                    String.format("Refund amount of %.2f exceeds total payment made on this order", amount));
        }

        refunds.add(new Refund(UUID.randomUUID().toString(), amount, method, memo));
    }

    @Nullable
    public Refund deleteRefund(String id) {
        for (Refund refund : refunds) {
            if (refund.getId().equals(id)) {
                refunds.remove(refund);
                return refund;
            }
        }
        return null;
    }

    private double getTotal() {
        return items.stream()
                .mapToDouble(item -> item.getNetPrice() * item.getQuantity())
                .sum();
    }

    private double getBalance() {
        return getTotal() - getTotalPayment();
    }

    private double getTotalPayment() {
        return payments.stream().mapToDouble(Payment::getAmount).sum();
    }

    private double getTotalRefund() {
        return refunds.stream().mapToDouble(Refund::getAmount).sum();
    }
}
