package dev.oscarrojas.order_manager.db;

import dev.oscarrojas.order_manager.core.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryInMemory implements OrderRepository {

    private final Map<String, OrderData> orders;

    public OrderRepositoryInMemory() {
        orders = new HashMap<>();
        List<ProductData> products = List.of(
                new ProductData("SHI-M-B", "shirt", "cotton dress shirt for men", Map.of("size", "M", "color", "blue")),
                new ProductData("SHI-L-R", "shirt", "cotton dress shirt for men", Map.of("size", "L", "color", "red")));
        orders.put(
                "100",
                new OrderData(
                        "100",
                        OrderStatus.PAID,
                        List.of(new OrderItemData(products.get(0), 2, 10), new OrderItemData(products.get(1), 1, 15)),
                        new CustomerData(
                                "customer1",
                                "John Smith",
                                "john@gmail.com",
                                "+1-915-404-1920",
                                new Address("123 Main St", "El Paso", "TX", "79901", "USA")),
                        new Address("123 Main St", "El Paso", "TX", "79901", "USA")));
    }

    @Override
    public List<Order> getAll() {
        return orders.values().stream()
                .map(order -> {
                    var customer = new Customer(
                            order.customer().id(),
                            order.customer().name(),
                            order.customer().email(),
                            order.customer().phone(),
                            order.customer().address());
                    var items = order.items().stream()
                            .map(item -> {
                                var product = new Product(
                                        item.product().sku(),
                                        item.product().name(),
                                        item.product().desc(),
                                        item.product().attributes());
                                return new OrderItem(product, item.quantity(), item.unitPrice());
                            })
                            .toList();
                    return new Order(order.id(), order.status(), items, customer, order.shippingAddress());
                })
                .toList();
    }

    @Override
    public void save(Collection<OrderData> orders) {
        for (OrderData order : orders) {
            this.orders.put(order.id(), order);
        }
    }
}
