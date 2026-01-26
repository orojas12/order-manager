package dev.oscarrojas.order_manager.db.customer;

import dev.oscarrojas.order_manager.customer.Customer;
import dev.oscarrojas.order_manager.customer.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepositoryInMemory implements CustomerRepository {

    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public Collection<Customer> getCustomers() {
        return List.copyOf(customers.values());
    }

    @Override
    public Optional<Customer> get(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public void save(Customer customer) {
        customers.put(customer.id(), customer);
    }

    @Override
    public void save(Collection<Customer> customers) {
        for (Customer customer : customers) {
            this.customers.put(customer.id(), customer);
        }
    }
}
