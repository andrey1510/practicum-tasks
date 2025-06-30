package com.reactivepractice.topic8;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/integer")
public class RandomIntegerController {

    @GetMapping("/random")
    public Mono<Integer> randomInt(
        @RequestParam int left,
        @RequestParam int right) {

        return Mono.fromCallable(() -> {
            if (left >= right) throw new IllegalArgumentException("Left >= right");
            return ThreadLocalRandom.current().nextInt(left, right);
        });
    }
}
