package com.redispractice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class PopularArticlesCacheService implements PopularArticleAware {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public void cache(String isbnNumber, String articleContent) {
        stringRedisTemplate.opsForValue().set(
            buildKey(isbnNumber),
            articleContent,
            Duration.ofDays(1).toDays(),
            TimeUnit.DAYS
        );
    }

    @Override
    public String getArticle(String isbnNumber) {
        String result = stringRedisTemplate.opsForValue().get(isbnNumber);
        stringRedisTemplate.expire(
            buildKey(isbnNumber),
            Duration.ofDays(1).toDays(),
            TimeUnit.DAYS
        );
        return result;
    }

    private static String buildKey(String isbnNumber) {
        return String.join(":", "articles", isbnNumber);
    }
}
