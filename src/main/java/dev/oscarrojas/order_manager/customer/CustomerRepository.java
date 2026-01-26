package dev.oscarrojas.order_manager.customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerRepository {

    Collection<Customer> getCustomers();

    Optional<Customer> get(String customerId);

    void save(Customer customer);

    void save(Collection<Customer> customers);
}
