package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.order.*;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import dev.oscarrojas.order_manager.web.forms.CreateOrderForm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final OrderService service;
    public static DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("MM/dd/yyyy").withZone(ZoneId.systemDefault());
    ;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/orders")
    public String get(Model model, @RequestParam(required = false, defaultValue = "1") int page) {
        int ordersPerPage = 50;
        List<OrderResponse> orders = service.getOrders();

        List<OrderView> orderViews = orders.stream()
                .skip((long) (page - 1) * ordersPerPage)
                .limit(ordersPerPage)
                .map(OrderController::mapToView)
                .toList();

        String pathName = "?page=";
        int lastPageNum = (int) Math.ceil((float) orders.size() / ordersPerPage);
        List<PaginationLink> paginationLinks = Pagination.createLinks(page, lastPageNum, pathName);

        model.addAttribute("orders", orderViews);
        model.addAttribute("paginationLinks", paginationLinks);

        return "orders";
    }

    @GetMapping("/order-details")
    public String getOrderDetails(Model model, @RequestParam String id) {
        if (!model.containsAttribute("order")) {
            Optional<OrderResponse> response = service.getOrderDetails(id);

            if (response.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }

            model.addAttribute("order", mapToView(response.get()));
        }

        return "order-details";
    }

    @GetMapping("/create-order")
    public String getCreateOrderForm() {
        return "create-order";
    }

    @PostMapping("/create-order")
    public String createOrder(RedirectAttributes model, CreateOrderForm formData) {

        List<CreateOrderItem> items = formData.items().stream()
                .map(item -> new CreateOrderItem(item.variantId(), item.quantity(), (long) item.price() * 100))
                .toList();

        CreateOrderRequest order = new CreateOrderRequest(items, formData.customerId(), formData.shippingAddress());

        OrderResponse response = service.createOrder(order);
        OrderView orderView = mapToView(response);

        model.addFlashAttribute("order", orderView);
        model.addFlashAttribute("isNew", true);

        return "redirect:/order-details?id=" + response.id();
    }

    public static OrderView mapToView(OrderResponse order) {
        List<OrderItemView> items = order.items().stream()
                .map(item -> new OrderItemView(
                        item.variant().id(),
                        item.variant().product().name(),
                        String.valueOf(item.quantity()),
                        String.valueOf(item.unitPrice() / 100.0)))
                .toList();

        CustomerView customer = new CustomerView(
                order.customer().id(),
                order.customer().name(),
                order.customer().email(),
                order.customer().phone(),
                order.customer().address());

        String orderTotal = String.format("%.2f", order.total() / 100.0);

        return new OrderView(
                order.id(),
                dateTimeFormatter.format(order.creationDate()),
                order.status(),
                orderTotal,
                items,
                customer,
                order.shippingAddress());
    }
}
