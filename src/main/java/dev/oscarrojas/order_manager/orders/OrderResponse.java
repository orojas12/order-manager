package dev.oscarrojas.order_manager.orders;

import dev.oscarrojas.order_manager.core.Address;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        Instant creationDate,
        String status,
        long total,
        List<OrderItemResponse> items,
        CustomerResponse customer,
        Address shippingAddress) {}
