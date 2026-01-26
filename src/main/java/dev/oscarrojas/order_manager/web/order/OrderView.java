package dev.oscarrojas.order_manager.web.order;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.web.customer.CustomerView;

import java.util.List;

public record OrderView(
        String id,
        String creationDate,
        String status,
        String total,
        List<OrderItemView> items,
        CustomerView customer,
        Address shippingAddress) {}
