package dev.oscarrojas.order_manager.config;

import dev.oscarrojas.order_manager.db.order.OrderRepository;
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

    public AppConfig(Environment env, OrderRepository orderRepository) {
        this.env = env;
        this.orderRepository = orderRepository;
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
            ClassPathResource resource = new ClassPathResource("fake-data/orders.json");
            JsonOrderDataLoader loader = new JsonOrderDataLoader(orderRepository);
            loader.load(resource);
        }
    }
}
