package dev.oscarrojas.order_manager.order;

import java.time.Instant;
import java.util.*;

import dev.oscarrojas.order_manager.customer.CustomerModel;
import dev.oscarrojas.order_manager.db.customer.CustomerRepository;
import dev.oscarrojas.order_manager.db.order.OrderRepository;
import dev.oscarrojas.order_manager.db.product.ProductVariantRepository;
import dev.oscarrojas.order_manager.exception.InvalidRequestException;
import dev.oscarrojas.order_manager.product.ProductResponse;
import dev.oscarrojas.order_manager.product.ProductVariantModel;
import dev.oscarrojas.order_manager.product.ProductVariantResponse;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductVariantRepository variantRepository;
    private final CustomerRepository customerRepository;

    private final CreateOrderRequestValidator createOrderRequestValidator;

    public OrderService(
            OrderRepository orderRepository,
            ProductVariantRepository variantRepository,
            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.variantRepository = variantRepository;
        this.customerRepository = customerRepository;
        this.createOrderRequestValidator = new CreateOrderRequestValidator();
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
        return orderRepository.getAll().stream()
                .map(this::mapToResponse)
                .sorted(Comparator.comparing(OrderResponse::creationDate).reversed())
                .toList();
    }

    public Optional<OrderResponse> getOrderDetails(String orderId) {
        Optional<OrderModel> opt = orderRepository.get(orderId);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        OrderModel data = opt.get();
        OrderResponse response = mapToResponse(data);
        return Optional.of(response);
    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        InvalidRequestException validationException = createOrderRequestValidator.validate(request);

        if (validationException != null) {
            throw validationException;
        }

        List<OrderLineModel> orderLines = new ArrayList<>();

        for (CreateOrderLine createOrderLine : request.lines()) {
            ProductVariantModel variant = variantRepository
                    .get(createOrderLine.variantId())
                    .orElseThrow(() -> new InvalidRequestException(
                            "Product variant id %s does not exist".formatted(createOrderLine.variantId())));
            orderLines.add(new OrderLineModel(variant, createOrderLine.quantity(), createOrderLine.unitPrice()));
        }

        CustomerModel customer = customerRepository
                .get(request.customerId())
                .orElseThrow(() ->
                        new InvalidRequestException("Customer id %s does not exist".formatted(request.customerId())));

        OrderModel order = new OrderModel.Builder()
                .id(UUID.randomUUID().toString())
                .status(OrderStatus.CREATED)
                .items(orderLines)
                .customer(customer)
                .shippingAddress(request.shippingAddress())
                .creationDate(Instant.now())
                .build();

        orderRepository.create(order);

        return mapToResponse(order);
    }
}
