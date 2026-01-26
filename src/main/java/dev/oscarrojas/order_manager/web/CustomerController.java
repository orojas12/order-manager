package dev.oscarrojas.order_manager.web;

import dev.oscarrojas.order_manager.customer.CreateCustomerRequest;
import dev.oscarrojas.order_manager.customer.CustomerService;
import dev.oscarrojas.order_manager.customer.CustomerResponse;
import dev.oscarrojas.order_manager.order.OrderService;
import dev.oscarrojas.order_manager.web.forms.CreateCustomerForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;

@Controller
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    public CustomerController(CustomerService customerService, OrderService orderService) {
        this.customerService = customerService;
        this.orderService = orderService;
    }

    @GetMapping("/customers")
    public String getCustomerList(Model model, Integer page) {
        page = Objects.requireNonNullElse(page, 1);
        int customersPerPage = 50;

        List<CustomerView> customers = customerService.getCustomers().stream()
                .skip((long) (page - 1) * customersPerPage)
                .limit(customersPerPage)
                .map(this::mapToView)
                .toList();

        String pathName = "?page=";
        int lastPage = (int) Math.ceil((float) customers.size() / customersPerPage);
        List<PaginationLink> paginationLinks = Pagination.createLinks(page, lastPage, pathName);

        model.addAttribute("customers", customers);
        model.addAttribute("paginationLinks", paginationLinks);

        return "customers/customers";
    }

    @GetMapping("/customers/create")
    public String createCustomerForm() {
        return "customers/create-customer";
    }

    @PostMapping("/customers/create")
    public String createCustomerFormSubmit(CreateCustomerForm form) {
        CreateCustomerRequest request =
                new CreateCustomerRequest(form.name(), form.email(), form.phone(), form.address());

        CustomerResponse response = customerService.createCustomer(request);

        CustomerView customer = mapToView(response);

        return "redirect:/customers";
    }

    private CustomerView mapToView(CustomerResponse customer) {
        return new CustomerView(
                customer.id(),
                customer.name(),
                customer.email() != null ? customer.email() : "--",
                customer.phone() != null ? customer.phone() : "--",
                customer.address());
    }

    @GetMapping("/customers/{id}")
    public String getCustomerDetails(Model model, @PathVariable String id) {
        List<OrderView> orders = orderService.getCustomerOrders(id).stream()
                .map(OrderController::mapToView)
                .toList();

        model.addAttribute("orders", orders);

        return "customers/customer-details";
    }
}
