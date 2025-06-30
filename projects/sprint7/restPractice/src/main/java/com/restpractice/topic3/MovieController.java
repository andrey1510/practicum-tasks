package com.restpractice.topic3;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/movies")
@RestController
class MovieController {

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID фильма должен быть положительным числом");
        }
        return new Movie(id, "Inception", "Christopher Nolan");
    }
}

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage(), 400));
    }
}

class ErrorResponse {
    private final String message;
    private final int errorCode;
    private final long timestamp;

    public ErrorResponse(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
    }
}

class Movie {
    private final Long id;
    private final String name;
    private final String director;

    Movie(Long id, String name, String director) {
        this.id = id;
        this.name = name;
        this.director = director;
    }
}
