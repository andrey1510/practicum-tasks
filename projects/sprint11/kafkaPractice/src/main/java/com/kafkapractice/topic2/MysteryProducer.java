package com.kafkapractice.topic2;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

@Slf4j
public class MysteryProducer {

    public static void main(String[] args) {
        String baseProducerName = args[0];
        Integer numberOfProducers = Integer.parseInt(args[1]);
        Random random = new Random();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        ExecutorService executor = newFixedThreadPool(numberOfProducers);
        for (int num = 1; num <= numberOfProducers; num++) {
            String consumerName = baseProducerName + "-" + num;
            executor.submit(() -> {
                while (true) {
                    try {
                         ProducerRecord<String, String> message =
                            new ProducerRecord<>(
                                "kafka-mystery",
                                null,
                                System.currentTimeMillis(),
                                String.valueOf(random.nextInt(1, 1000)),
                                String.format("%s - %s", consumerName, random.nextInt(1, 1000)));
                        producer.send(message);

                        Thread.sleep(1000);

                    } catch (RuntimeException e) {
                        log.error(e.getMessage());
                    }
                }
            });
        }
    }
}
