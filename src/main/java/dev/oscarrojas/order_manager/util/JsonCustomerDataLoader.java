package dev.oscarrojas.order_manager.util;

import dev.oscarrojas.order_manager.customer.CustomerModel;
import dev.oscarrojas.order_manager.db.customer.CustomerRepository;
import dev.oscarrojas.order_manager.db.order.OrderRepository;
import dev.oscarrojas.order_manager.order.OrderModel;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonCustomerDataLoader {

    private final CustomerRepository repository;

    public JsonCustomerDataLoader(CustomerRepository repository) {
        this.repository = repository;
    }

    public void load(ClassPathResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream stream = resource.getInputStream();

        List<CustomerModel> customers = mapper.readValue(stream, new TypeReference<List<CustomerModel>>() {});

        repository.save(customers);
    }
}
