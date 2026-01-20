package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.customer.CreateCustomerRequest;

import java.util.List;

public record CreateOrderAndCustomerRequest(
        List<CreateOrderLine> lines, CreateCustomerRequest customer, Address shippingAddress) {}
