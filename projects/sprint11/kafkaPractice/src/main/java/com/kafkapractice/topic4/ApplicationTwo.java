package com.kafkapractice.topic4;

import jakarta.annotation.Nullable;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.record.TimestampType;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationTwo {

    static class User { }

    static class UserStorage {
        private final Map<String, User> users = new ConcurrentHashMap<>();

        public void add(String key, User user) {
            Objects.requireNonNull(key);
            users.put(key, user);
        }

        @Nullable
        public User get(String key) {
            return users.get(key);
        }

    }

    static class IdempotencyUserRecordFilterStrategy implements RecordFilterStrategy<String, User> {

        private final UserStorage userStorage;

        public IdempotencyUserRecordFilterStrategy(UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public boolean filter(ConsumerRecord<String, User> consumerRecord) {
            if (consumerRecord.headers().lastHeader("idempotencyKey") == null) return false;
            return userStorage.get(new String(consumerRecord.headers().lastHeader("idempotencyKey").value(), StandardCharsets.UTF_8)) != null;
        }

    }

    public static void main(String[] args) {
        UserStorage userStorage = new UserStorage();
        userStorage.add("f5df4080-6439-4658-8ee8-5747b5abc344", new User());
        userStorage.add("edc3cd60-25e0-471e-bd5c-9b354d4d177f", new User());
        userStorage.add("36a615dd-3c86-41cf-a680-2bbcb60bb5ea", new User());
        userStorage.add("8497cc9a-10ce-4518-92cd-02d4962d72c2", new User());
        userStorage.add("79c7df8f-1640-4a3f-97a6-8939488a7f04", new User());
        userStorage.add("e708972c-9f66-43d1-be1a-4ec609a1a3dd", new User());

        RecordFilterStrategy<String, User> recordFilterStrategy = new IdempotencyUserRecordFilterStrategy(userStorage);
        System.out.print(recordFilterStrategy.filter(createRecord("f5df4080-6439-4658-8ee8-5747b5abc344")));
        System.out.print(recordFilterStrategy.filter(createRecord("96a1bc38-f90a-4548-877b-d9f29dbb165e")));
        System.out.print(recordFilterStrategy.filter(createRecord("36a615dd-3c86-41cf-a680-2bbcb60bb5ea")));
        System.out.print(recordFilterStrategy.filter(createRecord("79c7df8f-1640-4a3f-97a6-8939488a7f04")));
        System.out.print(recordFilterStrategy.filter(createRecord("40514903-cde3-4383-b3b9-4cbdd4e8cd74")));

    }

    static ConsumerRecord<String, User> createRecord(String idempotencyKey) {
        // Тестовая запись ConsumerRecord. Важен только заголовок idempotencyKey.
        return new ConsumerRecord<>(
            "users",
            0,
            0,
            Instant.now().toEpochMilli(),
            TimestampType.CREATE_TIME,
            -1,
            -1,
            idempotencyKey,
            new User(),
            new RecordHeaders(List.of(new RecordHeader("idempotencyKey", idempotencyKey.getBytes()))),
            Optional.empty()
        );
    }

}
