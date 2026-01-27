package dev.oscarrojas.order_manager.web.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ProductControllerTest {

    @Autowired
    private ProductController controller;

    @Test
    void createProduct() {
        // TODO: fix dumb spring security autowire error
    }
}
