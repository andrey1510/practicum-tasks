package com.springpractice.topic6;

import org.springframework.context.ApplicationEvent;

public class AliceMessageEvent extends ApplicationEvent {

    private final String message;

    public AliceMessageEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
