package dev.oscarrojas.order_manager.order;

import java.time.Instant;
import java.util.*;

import dev.oscarrojas.order_manager.customer.CustomerModel;
import dev.oscarrojas.order_manager.customer.CustomerRepository;
import dev.oscarrojas.order_manager.customer.CustomerResponse;
import dev.oscarrojas.order_manager.product.ProductVariantRepository;
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
        List<OrderItemResponse> lines = model.items().stream()
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

                    return new OrderItemResponse(variant, line.quantity(), line.unitPrice());
                })
                .toList();

        CustomerResponse customer = new CustomerResponse(
                model.customer().id(),
                model.customer().name(),
                model.customer().email(),
                model.customer().phone(),
                model.customer().address(),
                model.customer().dateCreated());

        long orderTotal = lines.stream().mapToLong(OrderItemResponse::unitPrice).sum();

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

    public List<OrderResponse> getCustomerOrders(String customerId) {
        return orderRepository.getOrdersByCustomer(customerId).stream()
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

        List<OrderItemModel> orderItems = new ArrayList<>();

        for (CreateOrderItem createOrderItem : request.items()) {
            ProductVariantModel variant = variantRepository
                    .get(createOrderItem.variantId())
                    .orElseThrow(() -> new InvalidRequestException(
                            "Product variant id %s does not exist".formatted(createOrderItem.variantId())));
            orderItems.add(new OrderItemModel(variant, createOrderItem.quantity(), createOrderItem.unitPrice()));
        }

        CustomerModel customer = customerRepository
                .get(request.customerId())
                .orElseThrow(() ->
                        new InvalidRequestException("Customer id %s does not exist".formatted(request.customerId())));

        OrderModel order = new OrderModel.Builder()
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
