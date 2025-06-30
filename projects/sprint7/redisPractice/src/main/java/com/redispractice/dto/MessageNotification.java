package com.redispractice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "messages", timeToLive = 1L)
public record MessageNotification(

    @Id
    String sender,

    @Indexed
    String recipient,

    String message
) {}