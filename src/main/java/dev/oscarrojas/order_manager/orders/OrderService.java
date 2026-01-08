package dev.oscarrojas.order_manager.orders;

import dev.oscarrojas.order_manager.core.OrderItem;
import dev.oscarrojas.order_manager.core.OrderStatus;
import dev.oscarrojas.order_manager.db.OrderCustomerData;
import dev.oscarrojas.order_manager.db.OrderData;
import dev.oscarrojas.order_manager.db.OrderItemData;
import dev.oscarrojas.order_manager.db.OrderRepository;
import dev.oscarrojas.order_manager.db.ProductData;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    private long getTotal(Collection<OrderItem> items) {
        return items.stream().mapToLong(OrderItem::getUnitPrice).sum();
    }

    private OrderResponse mapToResponse(OrderData data) {
        List<OrderItemResponse> items = data.items().stream()
                .map(item -> new OrderItemResponse(item.product().name(), item.quantity(), item.unitPrice()))
                .toList();

        CustomerResponse customer = new CustomerResponse(
                data.customer().name(), data.customer().email(), data.customer().phone());

        long orderTotal = items.stream().mapToLong(OrderItemResponse::unitPrice).sum();

        return new OrderResponse(
                data.id(), data.status().toString(), orderTotal, items, customer, data.shippingAddress());
    }

    public List<OrderResponse> getOrders() {
        return repository.getAll().stream().map(this::mapToResponse).toList();
    }

    public Optional<OrderResponse> getOrderDetails(String orderId) {
        Optional<OrderData> opt = repository.get(orderId);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        OrderData data = opt.get();
        OrderResponse response = mapToResponse(data);
        return Optional.of(response);
    }

    // TODO: add order validation
    public OrderResponse createOrder(CreateOrderAndCustomerRequest request) {
        List<OrderItemData> items = request.items().stream()
                .map(item -> new OrderItemData(
                        new ProductData(
                                item.product().sku(),
                                item.product().name(),
                                item.product().desc(),
                                item.product().attributes()),
                        item.quantity(),
                        item.unitPrice()))
                .toList();

        OrderCustomerData customer = new OrderCustomerData(
                UUID.randomUUID().toString(),
                request.customer().name(),
                request.customer().email(),
                request.customer().phone());

        OrderData order = new OrderData(
                UUID.randomUUID().toString(), OrderStatus.CREATED, items, customer, request.shippingAddress());

        repository.save(order);

        return mapToResponse(order);
    }
}
