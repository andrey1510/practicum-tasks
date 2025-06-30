package com.kafkapractice.topic2;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class MysteryConsumer {

    public static void main(String[] args) {
        String baseConsumerName = args[0];
        Integer numberOfConsumers = Integer.parseInt(args[1]);

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "localhost:9092");
        props.setProperty("group.id", "mystery-consumers");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        ExecutorService executor = Executors.newFixedThreadPool(numberOfConsumers);
        for (int num = 1; num <= numberOfConsumers; num++) {
            String consumerName = baseConsumerName + "-" + num;
            executor.submit(() -> {
                KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
                consumer.subscribe(Arrays.asList("kafka-mystery"));
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                    for (ConsumerRecord<String, String> record : records) {
                        log.info("текст: {}, ключ: {}, номер партиции: {}, офсет:{}, время: {}, имя потребителя:{}",
                            record.value(),
                            record.key(),
                            record.partition(),
                            record.offset(),
                            System.currentTimeMillis() - record.timestamp(),
                            consumerName
                            );
                    }
                }
            });
        }
    }
}
