package com.springbootpractice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", List.of("Дмитрий" , "Полина", "Иван", "Анна"));
        return "users";
    }
}
