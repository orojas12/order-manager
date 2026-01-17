package dev.oscarrojas.order_manager.core;

import jakarta.annotation.Nullable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class Order {

    private String id;
    private OrderStatus status;
    private List<OrderItem> items;
    private List<Payment> payments;
    private List<Refund> refunds;
    private Customer customer;
    private Address shippingAddress;
    private Instant creationDate;

    public Order() {
        items = new ArrayList<>();
        payments = new ArrayList<>();
        refunds = new ArrayList<>();
    }

    public Order(Builder builder) {
        this.id = builder.id;
        this.status = builder.status;
        this.items = builder.items;
        this.customer = builder.customer;
        this.shippingAddress =
                new Address(builder.street, builder.city, builder.state, builder.postalCode, builder.country);
        this.creationDate = builder.creationDate;
    }

    public Order(
            String id, OrderStatus status, Collection<OrderItem> items, Customer customer, Address shippingAddress) {
        this.id = id;
        this.status = status;
        this.items = new ArrayList<>(items);
        this.customer = customer;
        this.shippingAddress = shippingAddress;
        payments = new ArrayList<>();
        refunds = new ArrayList<>();
    }

    public String getId() {
        return id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Nullable
    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void pay(double amount, PaymentMethod method, String memo) throws OverPaymentException {

        if (amount > getBalance()) {
            throw new OverPaymentException(
                    String.format("Payment amount of %.2f exceeds remaining order balance", amount));
        }

        payments.add(new Payment(UUID.randomUUID().toString(), amount, method, memo));
    }

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
                .mapToDouble(item -> item.getUnitPrice() * item.getQuantity())
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

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public class Builder {
        private String id;
        private OrderStatus status;
        private List<OrderItem> items;
        private Customer customer;
        private String street;
        private String city;
        private String state;
        private String postalCode;
        private String country;
        private Instant creationDate;

        public Builder() {
            items = new ArrayList<>();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder status(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder item(OrderItem item) {
            items.add(item);
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder creationDate(Instant creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
