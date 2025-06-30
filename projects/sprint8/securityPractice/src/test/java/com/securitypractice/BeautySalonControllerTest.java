package com.securitypractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.util.List;

@SpringBootTest
@AutoConfigureWebTestClient
class BeautySalonControllerTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    void clientShouldAccessOwnAppointment() {
        var auth = new UsernamePasswordAuthenticationToken("anna", "password", List.of());

        webClient.mutateWith(SecurityMockServerConfigurers.mockAuthentication(auth))
            .get()
            .uri("/appointment/anna")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("Appointment details for: anna");
    }

    @Test
    void clientShouldNotAccessOtherAppointment() {
        var auth = new UsernamePasswordAuthenticationToken("maria", "password", List.of());

        webClient.mutateWith(SecurityMockServerConfigurers.mockAuthentication(auth))
            .get()
            .uri("/appointment/anna")
            .exchange()
            .expectStatus().isForbidden();
    }

    @Test
    void managerShouldAccessAnyAppointment() {
        var auth = new UsernamePasswordAuthenticationToken("manager", "password",
            List.of(new SimpleGrantedAuthority("ROLE_MANAGER")));

        webClient.mutateWith(SecurityMockServerConfigurers.mockAuthentication(auth))
            .get()
            .uri("/appointment/anna")
            .exchange()
            .expectStatus().isOk()
            .expectBody(String.class).isEqualTo("Appointment details for: anna");
    }
}
