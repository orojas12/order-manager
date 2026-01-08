package dev.oscarrojas.order_manager.orders;

import dev.oscarrojas.order_manager.core.Address;

import java.util.List;

public record CreateOrderAndCustomerRequest(
        List<CreateOrderItem> items, CreateCustomerRequest customer, Address shippingAddress) {}
