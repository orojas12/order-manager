package dev.oscarrojas.order_manager.db;

import dev.oscarrojas.order_manager.core.Address;

public record OrderCustomerData(String id, String name, String email, String phone) {}
