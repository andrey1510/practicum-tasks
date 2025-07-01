package com.springpractice.topic6;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Alice {

    private final ApplicationEventPublisher eventPublisher;
    private int helloCount;
    private int sentMessages = 0;

    public Alice(ApplicationEventPublisher eventPublisher, @Value("${alice.hello-count:5}") int helloCount) {
        this.eventPublisher = eventPublisher;
        this.helloCount = helloCount;
    }

    @EventListener
    public void onContextRefreshed(ContextRefreshedEvent event) {
        sendHelloToBob();
    }

    @EventListener
    public void onBobMessage(BobMessageEvent event) {
        if (sentMessages < helloCount) {
            sendHelloToBob();
        }
    }

    private void sendHelloToBob() {
        eventPublisher.publishEvent(new AliceMessageEvent(this, "Hello Bob #" + (sentMessages + 1)));
        sentMessages++;
    }
}
