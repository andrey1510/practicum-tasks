package com.kafkapractice.topic4;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;

public class UserProducer {
    private final KafkaTemplate<String, User> kafkaTemplate;

    public UserProducer(KafkaTemplate<String, User> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUser(User user) {
        ProducerRecord<String, User> record = new ProducerRecord<>(
            "users",
            user.getId(),
            user
        );

        record.headers()
            .add(new RecordHeader("idempotencyKey", user.getId().getBytes()))
            .add(new RecordHeader("createdAt", user.getCreatedAt().toString().getBytes()));

        kafkaTemplate.send(record);
    }
}
