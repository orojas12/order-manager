package dev.oscarrojas.order_manager.order;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {

    Collection<OrderModel> getAll();

    Collection<OrderModel> getOrdersByCustomer(String customerId);

    void create(OrderModel order);

    void create(Collection<OrderModel> orders);

    Optional<OrderModel> get(String id);
}
