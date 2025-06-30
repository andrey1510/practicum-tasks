package com.kafkapractice.topic6;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.DltStrategy;

public class ProcessMessage {

    @RetryableTopic(
        retryTopicSuffix = "-yandex-practicum-retry",
        dltTopicSuffix = "-yandex-practicum-dlt",
        dltStrategy = DltStrategy.FAIL_ON_ERROR
    )
    @KafkaListener(topics = "all-custom-topic")
    public void processMessage(String message) {
        // Обработка
    }
}
