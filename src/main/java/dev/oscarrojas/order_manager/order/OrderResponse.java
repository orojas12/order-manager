package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.Address;

import java.time.Instant;
import java.util.List;

public record OrderResponse(
        String id,
        Instant creationDate,
        String status,
        long total,
        List<OrderLineResponse> lines,
        CustomerResponse customer,
        Address shippingAddress) {}
