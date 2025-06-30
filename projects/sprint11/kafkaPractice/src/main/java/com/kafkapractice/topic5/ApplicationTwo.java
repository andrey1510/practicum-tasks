package com.kafkapractice.topic5;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.record.TimestampType;
import org.springframework.core.convert.converter.Converter;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ApplicationTwo {

    static class RecordConverter<K, V> implements Converter<ProducerRecord<K, V>, ConsumerRecord<K, V>> {

        private static long offset = 0L;

        @Override
        public ConsumerRecord<K, V> convert(ProducerRecord<K, V> source) {
            return new ConsumerRecord<>(
                source.topic(),
                source.partition(),
                offset++, // does not matter in this case
                source.timestamp(),
                TimestampType.CREATE_TIME,
                -1, // does not matter in this case
                -1, // does not matter in this case
                source.key(),
                source.value(),
                source.headers(),
                Optional.empty() // does not matter in this case
            );
        }
    }

    public static void main(String[] args) {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(
            "topic.test",
            0,
            Instant.now().toEpochMilli(),
            UUID.randomUUID().toString(),
            new Object(),
            new RecordHeaders()
        );

        producerRecord.headers().add(new RecordHeader("header.test-string", "value".getBytes(StandardCharsets.UTF_8)));
        producerRecord.headers().add(new RecordHeader("header.test-bool", String.valueOf(true).getBytes(StandardCharsets.UTF_8)));

        RecordConverter<String, Object> recordConverter = new RecordConverter<>();
        ConsumerRecord<String, Object> consumerRecord = recordConverter.convert(producerRecord);

        Headers headers = consumerRecord.headers();
        String stringHeader = new String(headers.lastHeader("header.test-string").value(), StandardCharsets.UTF_8);
        boolean boolHeader = Boolean.parseBoolean(new String(headers.lastHeader("header.test-bool").value(), StandardCharsets.UTF_8));

        System.out.print(stringHeader + boolHeader);
    }

}
