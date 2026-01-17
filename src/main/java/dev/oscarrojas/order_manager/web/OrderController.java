package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.core.OrderItem;
import dev.oscarrojas.order_manager.orders.*;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

import dev.oscarrojas.order_manager.web.forms.CreateOrderForm;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class OrderController {

    private final OrderService service;
    private final DateTimeFormatter dateTimeFormatter;

    public OrderController(OrderService service) {
        this.service = service;
        this.dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy").withZone(ZoneId.systemDefault());
    }

    @GetMapping("/orders")
    public String get(Model model, @RequestParam(required = false, defaultValue = "1") int page) {
        int ordersPerPage = 50;
        List<OrderResponse> orders = service.getOrders();

        List<OrderView> orderViews = orders.stream()
                .skip((long) (page - 1) * ordersPerPage)
                .limit(ordersPerPage)
                .map(this::mapToView)
                .toList();

        String pathName = "?page=";
        List<PaginationLink> paginationLinks = new ArrayList<>();

        int lastPageNum = (int) Math.ceil((float) orders.size() / ordersPerPage);

        if (page < 4) {
            for (int i = 1; i < 6; i++) {
                paginationLinks.add(new PaginationLink(String.valueOf(i), i, i == page, pathName + i));
            }
        } else if (page > lastPageNum - 2) {
            for (int i = page - 3; i < lastPageNum + 1; i++) {
                paginationLinks.add(new PaginationLink(String.valueOf(i), i, page == i, pathName + i));
            }
        } else {
            for (int i = page - 2; i < page + 3; i++) {
                paginationLinks.add(new PaginationLink(String.valueOf(i), i, i == page, pathName + i));
            }
        }

        if (page > 1) {
            paginationLinks.addFirst(new PaginationLink("Previous", page - 1, false, pathName + (page - 1)));
            paginationLinks.addFirst(new PaginationLink("First", 1, false, pathName + 1));
        }

        if (page < lastPageNum) {
            paginationLinks.addLast(new PaginationLink("Last", lastPageNum, false, pathName + lastPageNum));
            paginationLinks.addLast(new PaginationLink("Next", page + 1, false, pathName + (page + 1)));
        }

        model.addAttribute("orders", orderViews);
        model.addAttribute("paginationLinks", paginationLinks);

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
    public String createOrder(Model model, CreateOrderForm formData) {
        List<CreateOrderItem> items = formData.products().stream()
                .map(product -> new CreateOrderItem(
                        new CreateProduct(
                                UUID.randomUUID().toString(), product.name(), product.desc(), new HashMap<>()),
                        product.quantity(),
                        Math.round(product.unitPrice() * 100)))
                .toList();

        CreateCustomerRequest customer = new CreateCustomerRequest(
                formData.customer().name(),
                formData.customer().email(),
                formData.customer().phone());

        Address shippingAddress = new Address(
                formData.shippingAddress().street(),
                formData.shippingAddress().city(),
                formData.shippingAddress().state(),
                formData.shippingAddress().postalCode(),
                formData.shippingAddress().country());

        CreateOrderAndCustomerRequest request = new CreateOrderAndCustomerRequest(items, customer, shippingAddress);

        OrderResponse response = service.createOrder(request);
        OrderView orderView = mapToView(response);

        model.addAttribute("order", orderView);

        return "redirect:/order-details?id=" + response.id();
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
