package com.restpractice.topic4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
public class Application {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject("https://www.googleapis.com/oauth2/v3/certs", String.class);
        log.info(result);
    }

}
