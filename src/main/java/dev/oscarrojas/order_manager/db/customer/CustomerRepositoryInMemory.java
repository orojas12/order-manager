package dev.oscarrojas.order_manager.db.customer;

import dev.oscarrojas.order_manager.customer.CustomerModel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepositoryInMemory implements CustomerRepository {

    private final Map<String, CustomerModel> customers = new HashMap<>();

    @Override
    public Collection<CustomerModel> getCustomers() {
        return List.copyOf(customers.values());
    }

    @Override
    public Optional<CustomerModel> get(String customerId) {
        return Optional.ofNullable(customers.get(customerId));
    }

    @Override
    public void save(CustomerModel customer) {
        customers.put(customer.id(), customer);
    }

    @Override
    public void save(Collection<CustomerModel> customers) {
        for (CustomerModel customer : customers) {
            this.customers.put(customer.id(), customer);
        }
    }
}
