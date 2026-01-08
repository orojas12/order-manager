package dev.oscarrojas.order_manager.orders;

import dev.oscarrojas.order_manager.core.Address;

import java.util.List;

public record OrderResponse(
        String id,
        String status,
        long total,
        List<OrderItemResponse> items,
        CustomerResponse customer,
        Address shippingAddress) {}
