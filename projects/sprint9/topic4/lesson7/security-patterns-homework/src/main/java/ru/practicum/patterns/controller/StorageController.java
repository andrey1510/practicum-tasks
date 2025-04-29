package ru.practicum.patterns.controller;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.patterns.domain.Product;

@RestController
@RequestMapping("/api/storage")
public class StorageController {

    private static final List<Product> PRODUCTS = new ArrayList<>();

    @PostConstruct
    void setUp() {
        PRODUCTS.add(new Product("1", "Milk", 50));
        PRODUCTS.add(new Product("2", "Eggs", 100));
    }

    @GetMapping
    @PreAuthorize("hasRole('FARMER')")
    public List<Product> getAllProducts() {
        return PRODUCTS;
    }

    @PostMapping
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        PRODUCTS.add(product);
        return ResponseEntity.ok("Product added with ID: " + product.id());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        boolean removed = PRODUCTS.removeIf(product -> product.id().equals(id));
        if (removed) {
            return ResponseEntity.ok("Product removed");
        }
        return ResponseEntity.notFound().build();
    }
}
