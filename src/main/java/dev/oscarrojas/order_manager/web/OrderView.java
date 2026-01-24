package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.core.Address;
import java.util.List;

public record OrderView(
        String id,
        String creationDate,
        String status,
        String total,
        List<OrderLineView> items,
        CustomerView customer,
        Address shippingAddress) {}
