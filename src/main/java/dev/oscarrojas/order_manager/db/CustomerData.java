package dev.oscarrojas.order_manager.db;

import dev.oscarrojas.order_manager.core.Address;

public record CustomerData(String id, String name, String email, String phone, Address address) {}
