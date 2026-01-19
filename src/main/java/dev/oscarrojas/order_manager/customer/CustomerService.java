package dev.oscarrojas.order_manager.customer;

import dev.oscarrojas.order_manager.db.customer.CustomerRepository;
import dev.oscarrojas.order_manager.order.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<CustomerResponse> getCustomers() {
        return repository.getCustomers().stream()
                .map(customer ->
                        new CustomerResponse(customer.id(), customer.name(), customer.email(), customer.phone()))
                .toList();
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        CustomerModel customer =
                new CustomerModel(UUID.randomUUID().toString(), request.name(), request.email(), request.phone());

        repository.save(customer);

        return new CustomerResponse(customer.id(), customer.name(), customer.email(), customer.phone());
    }
}
