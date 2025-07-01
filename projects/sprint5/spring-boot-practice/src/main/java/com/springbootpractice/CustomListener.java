package com.springbootpractice;

import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomListener {
    @EventListener
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof AvailabilityChangeEvent ace) {
            System.out.println("Событие доступности приложения " +
                String.join(".", ace.getState().getClass().getSimpleName(), ace.getState().toString())
            );
        } else System.out.println("Событие: " + event.getClass().getSimpleName());
    }
}
