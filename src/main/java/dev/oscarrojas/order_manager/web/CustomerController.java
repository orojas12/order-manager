package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.customer.CustomerService;
import dev.oscarrojas.order_manager.order.CustomerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public String getCustomerList(Model model) {

        List<CustomerView> customers = service.getCustomers().stream()
                .map(customer -> new CustomerView(customer.id(), customer.name(), customer.email(), customer.phone()))
                .toList();

        model.addAttribute("customers", customers);

        return "customers";
    }

    @GetMapping("/create-customer")
    public String createCustomerForm() {
        return "create-customer";
    }
}
