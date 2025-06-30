package com.restpractice.topic4;

import org.springframework.web.reactive.function.client.WebClient;

public class ApplicationTwo {

    public static void main(String[] args) {
        WebClient webClient = WebClient.create();

        String responseBody = webClient.get()
            .uri("https://www.googleapis.com/oauth2/v3/certs")
            .exchangeToMono(r -> r.bodyToMono(String.class))
            .block();

        System.out.println(responseBody);
    }

}