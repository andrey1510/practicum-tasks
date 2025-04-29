package com.testproject2;

import java.time.LocalDateTime;

public class TaskService {

    public static void method(String userId, String orderDetails, boolean isSuccess) {
        String operationResult = switch (isSuccess) {
            case true -> "успешно завершена";
            case false -> "завершена c ошибкой";
        };
        String logEntry = String.format("Операция создания заказа: пользователь %s, время создания заказа %s, данные заказа: %s, результат операции: %s",
            userId, LocalDateTime.now(), orderDetails, operationResult);
        System.out.println(logEntry);
    }

    public static void main(String[] args) {
        String userId = "user123";
        String orderDetails = "Item1: 2 units, Item2: 1 unit";
        boolean isSuccess = true;

        method(userId, orderDetails, isSuccess);
    }
}