package dev.oscarrojas.order_manager.util;

import dev.oscarrojas.order_manager.db.OrderData;
import dev.oscarrojas.order_manager.db.OrderRepository;
import org.springframework.core.io.ClassPathResource;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonOrderDataLoader {

    private final OrderRepository repository;

    public JsonOrderDataLoader(OrderRepository repository) {
        this.repository = repository;
    }

    public void load(ClassPathResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream stream = resource.getInputStream();

        List<OrderData> orders = mapper.readValue(stream, new TypeReference<List<OrderData>>() {});

        repository.save(orders);
    }
}
