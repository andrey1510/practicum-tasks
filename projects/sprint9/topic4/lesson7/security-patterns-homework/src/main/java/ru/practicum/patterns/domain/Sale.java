package ru.practicum.patterns.domain;

public record Sale(
    String id,
    String productName,
    int quantity,
    double price
) {
}
