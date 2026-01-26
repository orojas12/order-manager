package dev.oscarrojas.order_manager.order;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {

    Collection<Order> getAll();

    Collection<Order> getOrdersByCustomer(String customerId);

    void create(Order order);

    void create(Collection<Order> orders);

    Optional<Order> get(String id);
}
