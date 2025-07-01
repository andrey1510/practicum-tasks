package com.springbootpractice;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;


@TestConfiguration
class TestWeatherConfig {

    @Bean
    public WeatherService mockWeatherService() {
        WeatherService mockService = Mockito.mock(WeatherService.class);
        Mockito.when(mockService.getWeather()).thenReturn("Sunny");
        return mockService;
    }
}

