package com.kafkapractice.topic2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // Производитель не должен ждать подтверждения доставки сообщения
        props.put("acks", "0");

        Producer<String, String> producer = new KafkaProducer<>(props);
        while (true) {
            Coordinates coordinates = getCoordinates();
            String messageText = coordinates.carId() + "/" + coordinates.x() + "/" + coordinates.y();
            producer.send(new ProducerRecord<>("cars-placement", coordinates.carId(), messageText), (metadata, exception) -> {
                if (exception != null) {
                    System.out.println("Не получилось отправить");
                }
            });
        }
    }

    private static Coordinates getCoordinates() {
        return new Coordinates("1", 1.0, 1.0);
        // Реализация метода - её писать не нужно
    }
}
