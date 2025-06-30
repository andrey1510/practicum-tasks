package com.securitypractice.topic4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
public class Config {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated())
                .sessionManagement(session -> session
                    .invalidSessionUrl("/session-expired")

                );
        return http.build();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, DataSource dataSource) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .rememberMe(remember -> remember
                .key("mySecretKey")
                .tokenRepository(persistentTokenRepository(dataSource))
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(604800 )
            );

        return http.build();
    }


    @Bean
    public PersistentTokenRepository persistentTokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);

        return repository;
    }
}
