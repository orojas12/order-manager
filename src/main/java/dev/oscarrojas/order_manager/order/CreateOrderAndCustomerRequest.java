package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;

import java.util.List;

public record CreateOrderAndCustomerRequest(List<CreateOrderLine> lines, String customerId, Address shippingAddress) {}
