package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;

import java.util.Collection;

public record CreateOrderRequest(Collection<CreateOrderItem> items, String customerId, Address shippingAddress) {}
