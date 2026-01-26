package dev.oscarrojas.order_manager.customer;

import dev.oscarrojas.order_manager.core.Address;

import java.time.Instant;

public record CustomerModel(String id, String name, String email, String phone, Address address, Instant dateCreated) {}
