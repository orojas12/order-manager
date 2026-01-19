package dev.oscarrojas.order_manager.db.customer;

import dev.oscarrojas.order_manager.customer.CustomerModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerRepositoryInMemory implements CustomerRepository {

    private final Map<String, CustomerModel> customers = new HashMap<>();

    @Override
    public Collection<CustomerModel> getCustomers() {
        return List.copyOf(customers.values());
    }

    @Override
    public void save(CustomerModel customer) {
        customers.put(customer.id(), customer);
    }
}
