package com.securitypractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class OrderPickupControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    @WithMockUser(username = "order123")
    void clientShouldAccessOwnOrder() {
        webClient.get()
            .uri("/order/order123")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("Order details for: order123");
    }
    @Test
    @WithMockUser(username = "order456")
    void clientShouldNotAccessOtherOrder() {
        webClient.get()
            .uri("/order/order123")
            .exchange()
            .expectStatus().isForbidden();
    }
    @Test
    @WithMockUser(username = "manager", roles = "MANAGER")
    void managerShouldAccessAnyOrder() {
        webClient.get()
            .uri("/order/order123")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("Order details for: order123");
    }
}
