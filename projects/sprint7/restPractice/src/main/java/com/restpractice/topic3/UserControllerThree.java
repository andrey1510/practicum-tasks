package com.restpractice.topic3;

import jakarta.validation.constraints.*;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;
import org.springframework.http.*;
import org.springframework.validation.*;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

interface OnCreate {}
interface OnUpdate {}

@RestController
@RequestMapping("/users")
@Validated
class UserControllerThree {
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok("Пользователь создан");    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok("Пользователь обновлён");    }
}

class UserRequest {
    @Null(groups = OnCreate.class, message = "ID должен быть null при создании")
    @NotNull(groups = OnUpdate.class, message = "ID обязателен при обновлении")
    private Long id;

    @NotBlank(message = "Имя обязательно")
    @Size(min = 2, max = 50, message = "Имя должно содержать от 2 до 50 символов")
    private String name;

    @Email(message = "Некорректный email")
    private String email;

    @Min(value = 18, message = "Возраст должен быть не менее 18 лет")
    private Integer age;


}


@RestControllerAdvice
class GlobalExceptionHandlerTwo {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
