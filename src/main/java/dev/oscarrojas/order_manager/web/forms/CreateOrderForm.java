package dev.oscarrojas.order_manager.web.forms;

import dev.oscarrojas.order_manager.core.Address;

import java.util.List;

public record CreateOrderForm(
        CreateOrderFormCustomer customer, Address shippingAddress, List<CreateOrderFormProduct> products) {}
