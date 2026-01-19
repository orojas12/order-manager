package dev.oscarrojas.order_manager.customer;

import dev.oscarrojas.order_manager.core.Address;

public record CreateCustomerRequest(String name, String email, String phone, Address address) {}
