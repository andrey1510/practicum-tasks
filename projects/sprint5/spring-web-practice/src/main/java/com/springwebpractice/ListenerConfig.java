package com.springwebpractice;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {
    @Bean
    public ServletListenerRegistrationBean<MyServletListener> myServletListener() {
        return new ServletListenerRegistrationBean<>(new MyServletListener());
    }
}
