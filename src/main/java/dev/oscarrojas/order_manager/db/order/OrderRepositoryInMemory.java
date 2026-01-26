package dev.oscarrojas.order_manager.db.order;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.oscarrojas.order_manager.order.Order;
import dev.oscarrojas.order_manager.order.OrderRepository;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryInMemory implements OrderRepository {

    private final Map<String, Order> orders;

    public OrderRepositoryInMemory() {
        orders = new HashMap<>();
    }

    @Override
    public Collection<Order> getAll() {
        return List.copyOf(orders.values());
    }

    @Override
    public Collection<Order> getOrdersByCustomer(String customerId) {
        return orders.values().stream()
                .filter(order -> order.customer().id().equals(customerId))
                .toList();
    }

    @Override
    public void create(Order order) {
        orders.put(order.id(), order);
    }

    @Override
    public void create(Collection<Order> orders) {
        for (Order order : orders) {
            this.orders.put(order.id(), order);
        }
    }

    @Override
    public Optional<Order> get(String id) {
        return Optional.ofNullable(orders.get(id));
    }
}
