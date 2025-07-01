package com.springpractice.topic5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

class Greeter {

    private final TimeSource timeSource;

    public Greeter(TimeSource timeSource) {
        this.timeSource = timeSource;
    }

    public String greet(String name) {
        LocalDateTime utc = timeSource.now().atZone(ZoneOffset.UTC).toLocalDateTime();
        return "Привет, " + name + "! Текущее время (UTC): " + DateTimeFormatter.ISO_DATE_TIME.format(utc);
    }

}

class TimeSource {

    private final Clock clock;

    public TimeSource(Clock clock) {
        this.clock = clock;
    }

    public Instant now() {
        return clock.instant();
    }

}

@Configuration
public class AppConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemDefaultZone();
    }

    @Bean
    public TimeSource timeSource(Clock clock) {
        return new TimeSource(clock);
    }

    @Bean
    public Greeter greeter(TimeSource timeSource) {
        return new Greeter(timeSource);
    }

}