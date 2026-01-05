package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.orders.OrderService;
import dev.oscarrojas.order_manager.orders.OrderView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    public String get(Model model) {
        List<OrderView> orders = service.getOrders();

        if (orders != null) {
            model.addAttribute("orders", orders);
        }

        return "orders";
    }
}
