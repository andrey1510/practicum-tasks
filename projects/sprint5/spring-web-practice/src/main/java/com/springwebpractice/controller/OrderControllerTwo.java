package com.springwebpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderControllerTwo {

    // Пример запроса: GET /orders?status=active&maxResult=10
    // Получить статус (строка) и максимальный результат (число)
    @GetMapping("/orders")
    public void getOrders(
        @RequestParam(name = "status", required = false, defaultValue = "all") String status,
        @RequestParam(name = "maxResult", required = false, defaultValue = "10") int maxResult) {

    }

    // Пример запроса: DELETE /orders/d4151a36
    // Получить идентификатор заказа (строка)
    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable("id") String orderId) {

    }

}
