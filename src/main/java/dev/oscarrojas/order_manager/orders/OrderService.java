package dev.oscarrojas.order_manager.orders;

import dev.oscarrojas.order_manager.db.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<OrderView> getOrders() {
        return repository.getAll().stream()
                .map(order -> {
                    return new OrderView(
                            order.getId(),
                            order.getStatus().toString(),
                            order.getItems().stream()
                                    .map(item -> new OrderItemView(
                                            item.getProduct().getName(), item.getQuantity(), item.getNetPrice()))
                                    .toList(),
                            new CustomerView(
                                    order.getCustomer().getName(),
                                    order.getCustomer().getEmail(),
                                    order.getCustomer().getPhone()),
                            order.getShippingAddress());
                })
                .toList();
    }
}
