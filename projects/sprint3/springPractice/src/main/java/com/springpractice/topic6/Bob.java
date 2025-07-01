package com.springpractice.topic6;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Bob {

    private final ApplicationEventPublisher eventPublisher;

    public Bob(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @EventListener
    public void onAliceMessage(AliceMessageEvent event) {
        System.out.println("Bob received: " + event.getMessage());
        eventPublisher.publishEvent(new BobMessageEvent(this, "Hello Alice, I got your message #" + event.getMessage()));
    }
}
