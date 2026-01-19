package dev.oscarrojas.order_manager.db.customer;

import dev.oscarrojas.order_manager.customer.CustomerModel;

import java.util.Collection;

public interface CustomerRepository {

    Collection<CustomerModel> getCustomers();

    void save(CustomerModel customer);
}
