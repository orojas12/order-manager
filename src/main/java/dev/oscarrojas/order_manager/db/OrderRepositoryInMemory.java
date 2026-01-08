package dev.oscarrojas.order_manager.db;

import dev.oscarrojas.order_manager.core.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryInMemory implements OrderRepository {

    private final Map<String, OrderData> orders;

    public OrderRepositoryInMemory() {
        orders = new HashMap<>();
    }

    @Override
    public Collection<OrderData> getAll() {
        return List.copyOf(orders.values());
    }

    @Override
    public void save(OrderData order) {
        orders.put(order.id(), order);
    }

    @Override
    public void save(Collection<OrderData> orders) {
        for (OrderData order : orders) {
            this.orders.put(order.id(), order);
        }
    }

    @Override
    public Optional<OrderData> get(String id) {
        return Optional.ofNullable(orders.get(id));
    }
}
