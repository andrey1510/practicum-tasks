package com.springbootpractice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Import(TestWeatherConfig.class)
class WeatherReporterTest {

    @Autowired
    private WeatherReporter weatherReporter;

    @Test
    void testReportWeather() {
        assertEquals("Today's weather: Sunny", weatherReporter.reportWeather());
    }
}
