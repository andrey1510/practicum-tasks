package ru.practicum.patterns.controller;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.patterns.domain.Animal;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    private static final List<Animal> ANIMALS = new ArrayList<>();


    @PostConstruct
    void setUp() {
        ANIMALS.add(new Animal("1", "Cow", 10));
        ANIMALS.add(new Animal("2", "Chicken", 50));
    }

    @GetMapping
    @PreAuthorize("hasRole('FARMER')")
    public List<Animal> getAllAnimals() {
        return ANIMALS;
    }

    @PostMapping
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<String> addAnimal(@RequestBody Animal animal) {
        ANIMALS.add(animal);
        return ResponseEntity.ok("Animal added with ID: " + animal.id());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<Animal> getAnimalById(@PathVariable String id) {
        return ANIMALS.stream()
            .filter(animal -> animal.id().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
