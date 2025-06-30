package com.redispractice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Supplier;

@Slf4j
@Service
public class PriceService implements IPriceService {

    @Override
    @Cacheable(value = "prices", key = "#name")
    public BigDecimal computePrice(String name, Supplier<BigDecimal> price) {
        log.info("Compute price for {}", name);
        return price.get();
    }

    @Override
    @CacheEvict(value = "prices", allEntries = true)
    public void clearPricesCache() {
        log.info("Clearing prices");
    }

    @Override
    @CacheEvict(value = "prices", key = "#name")
    public void evictPriceFromCache(String name) {
        log.info("Evicting price from cache: {}", name);
    }

    @Override
    @CachePut(value = "prices", key = "#name")
    public BigDecimal upsertPriceInCache(String name, BigDecimal price) {
        log.info("Updating price in cache: {}", name);
        return price;
    }

}
