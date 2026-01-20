package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.core.Address;

public record CustomerView(String id, String name, String email, String phone, Address address) {}
