package com.springbootpractice;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("indicatorName")
public class CustomCheck implements HealthIndicator {
    private boolean isUp = true;



    @Override
    public Health health() {
        isUp = !isUp;

        if (isUp) {
            return Health.up()
                .withDetail("state", "Включен")
                .withDetail("nextState", "Будет выключен")
                .build();
        } else {
            return Health.down()
                .withDetail("state", "Выключен")
                .withDetail("nextState", "Будет включен")
                .build();
        }
    }
}
