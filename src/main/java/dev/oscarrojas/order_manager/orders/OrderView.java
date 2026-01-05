package dev.oscarrojas.order_manager.orders;

import dev.oscarrojas.order_manager.core.Address;

import java.util.List;

public record OrderView(
        String id, String status, List<OrderItemView> items, CustomerView customer, Address shippingAddress) {}
