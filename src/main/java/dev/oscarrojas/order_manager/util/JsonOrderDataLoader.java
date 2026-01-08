package dev.oscarrojas.order_manager.util;

import dev.oscarrojas.order_manager.core.Address;
import dev.oscarrojas.order_manager.core.OrderStatus;
import dev.oscarrojas.order_manager.db.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.parameters.P;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JsonOrderDataLoader {

    private final OrderRepository repository;

    public JsonOrderDataLoader(OrderRepository repository) {
        this.repository = repository;
    }

    public void load(ClassPathResource resource) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream stream = resource.getInputStream();

        List<Map<String, Object>> orders = mapper.readValue(stream, new TypeReference<List<Map<String, Object>>>() {});

        List<OrderData> data = orders.stream()
                .map(order -> {
                    Map<String, String> customerMap = (Map<String, String>) order.get("customer");

                    OrderCustomerData customer = new OrderCustomerData(
                            (String) customerMap.get("id"),
                            (String) customerMap.get("name"),
                            (String) customerMap.get("email"),
                            (String) customerMap.get("phone"));

                    List<Map<String, Object>> itemMapList = (List<Map<String, Object>>) order.get("items");
                    List<OrderItemData> items = itemMapList.stream()
                            .map(itemMap -> {
                                var product = (Map<String, Object>) itemMap.get("product");
                                ProductData productData = new ProductData(
                                        (String) product.get("sku"),
                                        (String) product.get("name"),
                                        (String) product.get("desc"),
                                        (Map<String, String>) product.get("attributes"));
                                return new OrderItemData(
                                        productData,
                                        (int) itemMap.get("quantity"),
                                        Math.round(((double) itemMap.get("unitPrice")) * 100));
                            })
                            .toList();

                    Map<String, String> address = (Map<String, String>) order.get("shippingAddress");
                    Address shippingAddress = new Address(
                            address.get("street"),
                            address.get("city"),
                            address.get("state"),
                            address.get("postalCode"),
                            address.get("country"));

                    return new OrderData(
                            (String) order.get("id"),
                            OrderStatus.valueOf((String) order.get("status")),
                            items,
                            customer,
                            shippingAddress);
                })
                .toList();

        repository.save(data);
    }
}
