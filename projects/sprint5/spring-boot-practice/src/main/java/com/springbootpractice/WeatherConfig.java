package com.springbootpractice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherConfig {

    @Bean
    public WeatherService WeatherService() {
        return () -> "";
    }


}
