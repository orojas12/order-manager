package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.orders.CreateOrderAndCustomerRequest;
import dev.oscarrojas.order_manager.orders.OrderResponse;
import dev.oscarrojas.order_manager.orders.OrderService;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/orders")
    public String get(Model model) {
        List<OrderResponse> orders = service.getOrders();

        List<OrderView> orderViews = orders.stream().map(this::mapToView).toList();

        if (orders != null) {
            model.addAttribute("orders", orderViews);
        }

        return "orders";
    }

    @GetMapping("/order-details")
    public String getOrderDetails(Model model, @RequestParam String id) {
        Optional<OrderResponse> response = service.getOrderDetails(id);

        if (response.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        model.addAttribute("order", mapToView(response.get()));

        return "order-details";
    }

    @GetMapping("/create-order")
    public String getCreateOrderForm() {
        return "create-order";
    }

    @PostMapping("/orders")
    public String createOrder(Model model, @RequestBody CreateOrderAndCustomerRequest formData) {
        OrderResponse response = service.createOrder(formData);
        OrderView orderView = mapToView(response);

        model.addAttribute("order", orderView);

        return "order-details";
    }

    private OrderView mapToView(OrderResponse order) {
        List<OrderItemView> items = order.items().stream()
                .map(item -> new OrderItemView(
                        item.name(), String.valueOf(item.quantity()), String.valueOf(item.unitPrice() / 100.0)))
                .toList();

        CustomerView customer = new CustomerView(
                order.customer().name(),
                order.customer().email(),
                order.customer().phone());

        String orderTotal = String.format("%.2f", order.total() / 100.0);

        return new OrderView(order.id(), order.status(), orderTotal, items, customer, order.shippingAddress());
    }
}
