package dev.oscarrojas.order_manager.customer;

import dev.oscarrojas.order_manager.exception.InvalidRequestException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
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
                .map(customer -> new CustomerResponse(
                        customer.id(),
                        customer.name(),
                        customer.email(),
                        customer.phone(),
                        customer.address(),
                        customer.dateCreated()))
                .sorted(Comparator.comparing(CustomerResponse::dateCreated).reversed())
                .toList();
    }

    public CustomerResponse createCustomer(CreateCustomerRequest request) {
        CustomerModel customer = new CustomerModel(
                UUID.randomUUID().toString(),
                request.name(),
                request.email(),
                request.phone(),
                request.address(),
                Instant.now());

        InvalidRequestException exception = CreateCustomerValidator.validate(request);

        if (exception != null) {
            throw exception;
        }

        repository.save(customer);

        return new CustomerResponse(
                customer.id(),
                customer.name(),
                customer.email(),
                customer.phone(),
                customer.address(),
                customer.dateCreated());
    }
}
