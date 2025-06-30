package com.redispractice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;

@RedisHash(value = "cars", timeToLive = 10)
public record CarPrice(
    @Id
    String name,

    BigDecimal price
){}
