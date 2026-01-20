package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.customer.CustomerService;
import dev.oscarrojas.order_manager.order.CustomerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/customers")
    public String getCustomerList(Model model, Integer page) {
        page = Objects.requireNonNullElse(page, 1);
        int customersPerPage = 50;

        List<CustomerView> customers = service.getCustomers().stream()
                .skip((long) (page - 1) * customersPerPage)
                .limit(customersPerPage)
                .map(this::mapToView)
                .toList();

        String pathName = "?page=";
        int lastPage = (int) Math.ceil((float) customers.size() / customersPerPage);
        List<PaginationLink> paginationLinks = Pagination.createLinks(page, lastPage, pathName);

        model.addAttribute("customers", customers);
        model.addAttribute("paginationLinks", paginationLinks);

        return "customers";
    }

    @GetMapping("/create-customer")
    public String createCustomerForm() {
        return "create-customer";
    }

    private CustomerView mapToView(CustomerResponse customer) {
        return new CustomerView(
                customer.id(),
                customer.name(),
                customer.email() != null ? customer.email() : "--",
                customer.phone() != null ? customer.phone() : "--",
                customer.address());
    }
}
