package dev.oscarrojas.order_manager.orders;

import dev.oscarrojas.order_manager.core.OrderItem;
import dev.oscarrojas.order_manager.db.OrderItemData;
import dev.oscarrojas.order_manager.db.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    private String getTotal(Collection<OrderItem> items) {
        BigDecimal total = new BigDecimal(0);
        for (OrderItem item : items) {
            BigDecimal quant = new BigDecimal(item.getQuantity());
            BigDecimal price = new BigDecimal(item.getUnitPrice());
            total = total.add(quant.multiply(price));
        }
        return total.toString();
        // TODO: find a way to display total price with 2 decimal places
    }

    public List<OrderView> getOrders() {
        return repository.getAll().stream()
                .map(order -> {
                    return new OrderView(
                            order.getId(),
                            order.getStatus().toString(),
                            getTotal(order.getItems()),
                            order.getItems().stream()
                                    .map(item -> new OrderItemView(
                                            item.getProduct().getName(), item.getQuantity(), item.getUnitPrice()))
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
