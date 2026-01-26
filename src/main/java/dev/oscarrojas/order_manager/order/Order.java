package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.customer.Customer;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record Order(
        String id,
        OrderStatus status,
        Collection<OrderItem> items,
        Customer customer,
        Address shippingAddress,
        Instant creationDate) {

    public Order {
        items = List.copyOf(items);
    }

    public static class Builder {
        private String id;
        private OrderStatus status;
        private Collection<OrderItem> items;
        private Customer customer;
        private Address address;
        private Instant creationDate;

        public Builder() {
            this.items = new ArrayList<>();
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

        public Builder items(Collection<OrderItem> items) {
            this.items = items;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder shippingAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder creationDate(Instant creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public Order build() {
            return new Order(id, status, items, customer, address, creationDate);
        }
    }
}
