package dev.sivalabs.bookstore;

import dev.sivalabs.bookstore.notification.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

class BookStoreModulithApplicationTests extends BaseIT {

    @MockitoBean
    EmailService emailService;

    @Test
    void contextLoads() {
        restTestClient.get().uri("/actuator/health")
                .exchange()
                .expectStatus().isOk();
    }

}
