package com.securitypractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void userShouldAccessOwnAccount() throws Exception {
        var auth = new UsernamePasswordAuthenticationToken(
            "masha",
            null,
            List.of());
        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(get("/account/masha"))
            .andExpect(status().isOk())
            .andExpect(content().string("Account of masha"));

        SecurityContextHolder.clearContext();
    }

    @Test
    void userShouldNotAccessOtherAccount() throws Exception {
        var auth = new UsernamePasswordAuthenticationToken(
            "dasha",
            null,
            List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(get("/account/masha"))
            .andExpect(status().isForbidden());

        SecurityContextHolder.clearContext();
    }

    @Test
    void supportUserShouldAccessAnyAccount() throws Exception {
        var auth = new UsernamePasswordAuthenticationToken(
            "support_user",
            null,
            List.of(new SimpleGrantedAuthority("ROLE_SUPPORT")));
        SecurityContextHolder.getContext().setAuthentication(auth);

        mockMvc.perform(get("/account/masha"))
            .andExpect(status().isOk())
            .andExpect(content().string("Account of masha"));

        SecurityContextHolder.clearContext();
    }
}
