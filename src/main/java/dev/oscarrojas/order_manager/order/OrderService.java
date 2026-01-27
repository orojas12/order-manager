package dev.oscarrojas.order_manager.order;

import java.time.Instant;
import java.util.*;

import dev.oscarrojas.order_manager.customer.Customer;
import dev.oscarrojas.order_manager.customer.CustomerRepository;
import dev.oscarrojas.order_manager.customer.CustomerResponse;
import dev.oscarrojas.order_manager.exception.InvalidRequestException;
import dev.oscarrojas.order_manager.product.*;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final ProductVariantMapper variantMapper = new ProductVariantMapper();
    private final ProductMapper productMapper = new ProductMapper(variantMapper);

    private final CreateOrderRequestValidator createOrderRequestValidator;

    public OrderService(
            OrderRepository orderRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.createOrderRequestValidator = new CreateOrderRequestValidator();
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> items = order.items().stream()
                .map(item -> {
                    ProductVariantResponse variant = variantMapper.toResponse(item.variant());
                    ProductResponse product = productMapper.toResponse(item.product());
                    return new OrderItemResponse(product, variant, item.quantity(), item.unitPrice());
                })
                .toList();

        CustomerResponse customer = new CustomerResponse(
                order.customer().id(),
                order.customer().name(),
                order.customer().email(),
                order.customer().phone(),
                order.customer().address(),
                order.customer().dateCreated());

        long orderTotal = items.stream().mapToLong(OrderItemResponse::unitPrice).sum();

        return new OrderResponse(
                order.id(),
                order.creationDate(),
                order.status().toString(),
                orderTotal,
                items,
                customer,
                order.shippingAddress());
    }

    public List<OrderResponse> getOrders() {
        return orderRepository.getAll().stream()
                .map(this::mapToResponse)
                .sorted(Comparator.comparing(OrderResponse::creationDate).reversed())
                .toList();
    }

    public List<OrderResponse> getCustomerOrders(String customerId) {
        return orderRepository.getOrdersByCustomer(customerId).stream()
                .map(this::mapToResponse)
                .sorted(Comparator.comparing(OrderResponse::creationDate).reversed())
                .toList();
    }

    public Optional<OrderResponse> getOrderDetails(String orderId) {
        Optional<Order> opt = orderRepository.get(orderId);

        if (opt.isEmpty()) {
            return Optional.empty();
        }

        Order data = opt.get();
        OrderResponse response = mapToResponse(data);
        return Optional.of(response);
    }

    public OrderResponse createOrder(CreateOrderRequest request) {

        InvalidRequestException validationException = createOrderRequestValidator.validate(request);

        if (validationException != null) {
            throw validationException;
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CreateOrderItem createOrderItem : request.items()) {
            ProductVariant variant = productRepository
                    .getVariantById(createOrderItem.variantId())
                    .orElseThrow(() -> new InvalidRequestException(
                            "Product variant id %s does not exist".formatted(createOrderItem.variantId())));
            Product product = productRepository
                    .getById(variant.productId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Orphaned product variant: product id %s does not exist".formatted(variant.productId())));
            orderItems.add(new OrderItem(product, variant, createOrderItem.quantity(), createOrderItem.unitPrice()));
        }

        Customer customer = customerRepository
                .get(request.customerId())
                .orElseThrow(() ->
                        new InvalidRequestException("Customer id %s does not exist".formatted(request.customerId())));

        Order order = new Order.Builder()
                .id(UUID.randomUUID().toString())
                .status(OrderStatus.CREATED)
                .items(orderItems)
                .customer(customer)
                .shippingAddress(request.shippingAddress())
                .creationDate(Instant.now())
                .build();

        orderRepository.create(order);

        return mapToResponse(order);
    }
}
