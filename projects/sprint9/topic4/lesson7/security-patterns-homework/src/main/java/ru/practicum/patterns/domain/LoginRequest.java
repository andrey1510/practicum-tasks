package ru.practicum.patterns.domain;

public record LoginRequest(
    String username,
    String password
) {}