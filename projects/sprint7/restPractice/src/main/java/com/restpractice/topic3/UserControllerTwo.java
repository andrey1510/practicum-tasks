package com.restpractice.topic3;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/users")
class UserControllerTwo {

    @GetMapping("/{id}")
    public ResponseEntity<UserTwo> getUser(@PathVariable Long id) {
        UserTwo user = findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<UserTwo> createUser(@RequestBody UserTwo user) {
        UserTwo savedUser = saveUser(user);
        URI location = URI.create("/users/" + savedUser.getId());
        return ResponseEntity.created(location).body(savedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        UserTwo user = deleteUserById(id);
        if (user != null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/check/id")
    public ResponseEntity<String> checkId(@RequestParam(required = false) Long id) {
        if (id == null || id < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Ошибка: ID должен быть положительным числом!");
        }
        return ResponseEntity.ok("ID корректен: " + id);
    }

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirectToNewResource() {
        URI location = URI.create("/users/1");
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(location)
            .build();
    }

    // Методы-заглушки
    private UserTwo findUserById(Long id) {
        if (id == 1) {
            return new UserTwo(1L, "Иван", "ivan@yandex.ru");
        }
        return null;
    }

    private UserTwo deleteUserById(Long id) {
        if (id == 1) {
            return new UserTwo(1L, "Иван", "ivan@yandex.ru");
        }
        return null;
    }

    private UserTwo saveUser(UserTwo user) {
        user.setId(2L);
        return user;
    }
}

class UserTwo {
    private Long id;
    private String name;
    private String email;

    public UserTwo(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
