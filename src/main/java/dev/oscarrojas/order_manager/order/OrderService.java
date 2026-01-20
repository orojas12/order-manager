package dev.oscarrojas.order_manager.order;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import dev.oscarrojas.order_manager.customer.CustomerModel;
import dev.oscarrojas.order_manager.db.order.OrderRepository;
import dev.oscarrojas.order_manager.product.ProductResponse;
import dev.oscarrojas.order_manager.product.ProductVariantResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    private OrderResponse mapToResponse(OrderModel model) {
        List<OrderLineResponse> lines = model.lines().stream()
                .map(line -> {
                    ProductResponse product = new ProductResponse(
                            line.variant().product().id(),
                            line.variant().product().brand(),
                            line.variant().product().name(),
                            line.variant().product().description());

                    ProductVariantResponse variant = new ProductVariantResponse(
                            line.variant().id(),
                            line.variant().sku(),
                            product,
                            line.variant().attributes());

                    return new OrderLineResponse(variant, line.quantity(), line.unitPrice());
                })
                .toList();

        CustomerResponse customer = new CustomerResponse(
                model.customer().id(),
                model.customer().name(),
                model.customer().email(),
                model.customer().phone(),
                model.customer().address());

        long orderTotal = lines.stream().mapToLong(OrderLineResponse::unitPrice).sum();

        return new OrderResponse(
                model.id(),
                model.creationDate(),
                model.status().toString(),
                orderTotal,
                lines,
                customer,
                model.shippingAddress());
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

        List<OrderLineModel> orderLines = request.lines().stream()
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
                request.customer().phone(),
                request.customer().address());

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
