package com.springbootpractice;

import org.springframework.stereotype.Service;

@Service
class WeatherReporter {

    private final WeatherService weatherService;

    public WeatherReporter(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    public String reportWeather() {
        return "Today's weather: " + weatherService.getWeather();
    }
}
