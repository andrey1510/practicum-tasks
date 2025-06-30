package com.securitypractice.topic4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/info", "/help").anonymous()
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .anonymous(anonymous -> anonymous
                .principal("guestUser")
                .authorities("ROLE_GUEST")
                .key("uniqueAnonymousKey")
            );

        return http.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/custom-logout")
                .logoutSuccessUrl("/goodbye")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("remember-me")
                .permitAll());
        return http.build();
    }

}
