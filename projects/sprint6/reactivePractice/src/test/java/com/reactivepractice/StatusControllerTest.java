package com.reactivepractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(StatusController.class)
public class StatusControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void testStatusEndpoint() {
        webTestClient.get()
            .uri("/status")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON);
    }
}