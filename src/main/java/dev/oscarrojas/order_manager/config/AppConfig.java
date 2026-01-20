package dev.oscarrojas.order_manager.config;

import dev.oscarrojas.order_manager.db.customer.CustomerRepository;
import dev.oscarrojas.order_manager.db.order.OrderRepository;
import dev.oscarrojas.order_manager.util.JsonCustomerDataLoader;
import dev.oscarrojas.order_manager.util.JsonOrderDataLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
public class AppConfig {

    private final Environment env;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public AppConfig(Environment env, OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.env = env;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    @EventListener
    public void onAppContextRefresh(ContextRefreshedEvent event) throws IOException {
        if (env.matchesProfiles("dev")) {
            ClassPathResource orders = new ClassPathResource("fake-data/orders.json");
            JsonOrderDataLoader orderLoader = new JsonOrderDataLoader(orderRepository);
            orderLoader.load(orders);

            ClassPathResource customers = new ClassPathResource("fake-data/customers.json");
            JsonCustomerDataLoader customerLoader = new JsonCustomerDataLoader(customerRepository);
            customerLoader.load(customers);
        }
    }
}
