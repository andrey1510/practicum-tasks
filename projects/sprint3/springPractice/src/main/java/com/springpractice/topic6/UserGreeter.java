package com.springpractice.topic6;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class UserGreeter {

    private final MessageSource messageSource;

    public UserGreeter(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getGreetMessage(String username, Locale locale) {
        return messageSource.getMessage("user.hello",
            new Object[]{
                username,
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(messageSource.getMessage("date.format", null, locale)))
            },
            locale);
    }

}

