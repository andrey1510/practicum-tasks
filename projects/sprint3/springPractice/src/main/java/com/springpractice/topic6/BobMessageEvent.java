package com.springpractice.topic6;

import org.springframework.context.ApplicationEvent;

public class BobMessageEvent extends ApplicationEvent {

    private final String message;

    public BobMessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
