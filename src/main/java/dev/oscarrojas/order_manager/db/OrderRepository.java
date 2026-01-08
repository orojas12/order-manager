package dev.oscarrojas.order_manager.db;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository {
    Collection<OrderData> getAll();

    void save(OrderData order);

    void save(Collection<OrderData> orders);

    Optional<OrderData> get(String id);
}
