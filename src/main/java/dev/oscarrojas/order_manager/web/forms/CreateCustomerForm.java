package dev.oscarrojas.order_manager.web.forms;

import dev.oscarrojas.order_manager.core.Address;

public record CreateCustomerForm(String name, String email, String phone, Address address) {}
