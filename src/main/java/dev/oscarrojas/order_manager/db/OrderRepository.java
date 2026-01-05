package dev.oscarrojas.order_manager.db;

import dev.oscarrojas.order_manager.core.Order;

import java.util.List;

public interface OrderRepository {
    List<Order> getAll();
}
