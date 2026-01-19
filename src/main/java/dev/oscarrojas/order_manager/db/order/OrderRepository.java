package dev.oscarrojas.order_manager.db.order;

import dev.oscarrojas.order_manager.order.OrderModel;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {
    Collection<OrderModel> getAll();

    void save(OrderModel order);

    void save(Collection<OrderModel> orders);

    Optional<OrderModel> get(String id);
}
