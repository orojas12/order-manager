package dev.oscarrojas.order_manager.db;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.core.OrderStatus;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record OrderData(
        String id,
        OrderStatus status,
        Collection<OrderItemData> items,
        OrderCustomerData customer,
        Address shippingAddress,
        Instant creationDate) {

    public OrderData {
        items = List.copyOf(items);
    }

    public static class Builder {
        private String id;
        private OrderStatus status;
        private Collection<OrderItemData> items;
        private OrderCustomerData customer;
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

        public Builder item(OrderItemData item) {
            items.add(item);
            return this;
        }

        public Builder items(Collection<OrderItemData> items) {
            this.items = items;
            return this;
        }

        public Builder customer(OrderCustomerData customer) {
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

        public OrderData build() {
            return new OrderData(id, status, items, customer, address, creationDate);
        }
    }
}
