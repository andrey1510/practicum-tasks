package com.kafkapractice.topic4;

import java.time.Instant;

class User {
    private final String id;
    private final Instant createdAt;

    public User(String id, Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
