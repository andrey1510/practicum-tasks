package com.kafkapractice.topic3;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Scanner;

@Slf4j
public class CalculatorProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("transactional.id", "calculator-producer-id");
        props.put("transaction.timeout.ms", "300000");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.initTransactions();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                producer.beginTransaction();

                System.out.println("Наберите число");
                Integer inputNumber = scanner.nextInt();
                scanner.nextLine();
                producer.send(new ProducerRecord<>(
                    "input-numbers",
                    String.valueOf(inputNumber % 10),
                    String.valueOf(inputNumber))
                );

                System.out.println("Наберите операцию ");
                String operation = scanner.nextLine();
                Integer result = switch (operation) {
                    case "sq" -> inputNumber * inputNumber;
                    case "abs" -> Math.abs(inputNumber);
                    default -> throw new IllegalArgumentException("Неправильный ввод");
                };
                producer.send(new ProducerRecord<>(
                    "results",
                    String.valueOf(inputNumber % 10), inputNumber + " " + operation + " " + result)
                );

                producer.commitTransaction();
            } catch (RuntimeException e) {
                log.error(e.getMessage());

                producer.abortTransaction();
            }
        }
    }
}
