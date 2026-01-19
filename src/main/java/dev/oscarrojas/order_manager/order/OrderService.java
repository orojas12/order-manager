package dev.oscarrojas.order_manager.order;

import dev.oscarrojas.order_manager.core.OrderStatus;
import dev.oscarrojas.order_manager.db.*;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.oscarrojas.order_manager.customer.CustomerModel;
import dev.oscarrojas.order_manager.db.order.OrderLineModel;
import dev.oscarrojas.order_manager.db.order.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    private OrderResponse mapToResponse(OrderModel data) {
        List<OrderLineResponse> lines = data.items().stream()
                .map(line -> new OrderLineResponse(
                        line.productVariant().sku(), line.productVariant().name(), line.quantity(), line.unitPrice()))
                .toList();

        CustomerResponse customer = new CustomerResponse(
                data.customer().id(),
                data.customer().name(),
                data.customer().email(),
                data.customer().phone());

        long orderTotal = lines.stream().mapToLong(OrderLineResponse::unitPrice).sum();

        return new OrderResponse(
                data.id(),
                data.creationDate(),
                data.status().toString(),
                orderTotal,
                lines,
                customer,
                data.shippingAddress());
    }

    public List<OrderResponse> getOrders() {
        return repository.getAll().stream()
                .map(this::mapToResponse)
                .sorted(Comparator.comparing(OrderResponse::creationDate).reversed())
                .toList();
    }

    public Optional<OrderResponse> getOrderDetails(String orderId) {
        Optional<OrderModel> opt = repository.get(orderId);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        OrderModel data = opt.get();
        OrderResponse response = mapToResponse(data);
        return Optional.of(response);
    }

    // TODO: add order validation
    public OrderResponse createOrder(CreateOrderAndCustomerRequest request) {

        List<OrderLineModel> orderLines = request.items().stream()
                .map(item -> new OrderLineModel(
                        new ProductVariantModel(
                                item.product().sku(),
                                item.product().name(),
                                item.product().desc(),
                                item.product().attributes()),
                        item.quantity(),
                        item.unitPrice()))
                .toList();

        CustomerModel customer = new CustomerModel(
                UUID.randomUUID().toString(),
                request.customer().name(),
                request.customer().email(),
                request.customer().phone());

        OrderModel order = new OrderModel.Builder()
                .id(UUID.randomUUID().toString())
                .status(OrderStatus.CREATED)
                .items(orderLines)
                .customer(customer)
                .shippingAddress(request.shippingAddress())
                .creationDate(Instant.now())
                .build();

        repository.save(order);

        return mapToResponse(order);
    }
}
