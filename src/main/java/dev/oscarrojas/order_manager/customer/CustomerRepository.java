package dev.oscarrojas.order_manager.customer;

import java.util.Collection;
import java.util.Optional;

public interface CustomerRepository {

    Collection<CustomerModel> getCustomers();

    Optional<CustomerModel> get(String customerId);

    void save(CustomerModel customer);

    void save(Collection<CustomerModel> customers);
}
