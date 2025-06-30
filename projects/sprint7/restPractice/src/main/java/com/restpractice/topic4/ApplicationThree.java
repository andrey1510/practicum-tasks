package com.restpractice.topic4;

import org.springframework.web.client.RestClient;

public class ApplicationThree {

    public static void main(String[] args) {
        RestClient restClient = RestClient.create();

        String responseBody = restClient.get()
            .uri("https://www.googleapis.com/oauth2/v3/certs")
            .retrieve()
            .body(String.class);

        System.out.println(responseBody);
    }

}
