package com.springwebpractice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("message", "Привет, Spring WebMVC!");
        return "hello"; // Возвращает имя представления (hello.html)
    }

    @GetMapping("/user")
    public String getUserInfo(Model model) {
        Map<String, String> user = Map.of(
            "name", "Иван Иванов",
            "email", "ivan@example.com",
            "role", "admin"
        );

        model.addAttribute("user", user);
        return "user"; // Возвращает шаблон user.html
    }
}
