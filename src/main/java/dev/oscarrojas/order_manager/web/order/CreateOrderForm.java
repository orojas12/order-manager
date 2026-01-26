package dev.oscarrojas.order_manager.web.order;

import dev.oscarrojas.order_manager.core.Address;

import java.util.List;

public record CreateOrderForm(String customerId, Address shippingAddress, List<CreateOrderItemForm> items) {}
