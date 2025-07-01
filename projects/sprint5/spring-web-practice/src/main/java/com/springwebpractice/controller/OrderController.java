package com.springwebpractice.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/orders")
public class OrderController {

    @GetMapping
    public void getOrders(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
    }

    @PostMapping
    public void saveOrder(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
    }

    @DeleteMapping
    public void deleteOrders(HttpServletResponse response) {
        response.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
    }
}
