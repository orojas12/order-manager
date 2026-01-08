package dev.oscarrojas.order_manager.db;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.core.OrderStatus;

import java.util.Collection;

public record OrderData(
        String id,
        OrderStatus status,
        Collection<OrderItemData> items,
        OrderCustomerData customer,
        Address shippingAddress) {}
