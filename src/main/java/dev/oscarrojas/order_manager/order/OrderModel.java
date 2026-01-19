package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.core.OrderStatus;
import dev.oscarrojas.order_manager.customer.CustomerModel;
import dev.oscarrojas.order_manager.db.order.OrderLineModel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public record OrderModel(
        String id,
        OrderStatus status,
        Collection<OrderLineModel> items,
        CustomerModel customer,
        Address shippingAddress,
        Instant creationDate) {

    public OrderModel {
        items = List.copyOf(items);
    }

    public static class Builder {
        private String id;
        private OrderStatus status;
        private Collection<OrderLineModel> items;
        private CustomerModel customer;
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

        public Builder item(OrderLineModel item) {
            items.add(item);
            return this;
        }

        public Builder items(Collection<OrderLineModel> items) {
            this.items = items;
            return this;
        }

        public Builder customer(CustomerModel customer) {
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

        public OrderModel build() {
            return new OrderModel(id, status, items, customer, address, creationDate);
        }
    }
}
