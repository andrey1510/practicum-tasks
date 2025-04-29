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
import ru.practicum.patterns.domain.Crop;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    private static final List<Crop> CROPS = new ArrayList<>();

    @PostConstruct
    void setUp() {
        CROPS.add(new Crop("1", "Wheat", 100));
        CROPS.add(new Crop("2", "Corn", 200));
    }

    @GetMapping
    @PreAuthorize("hasRole('FARMER')")
    public List<Crop> getAllCrops() {
        return CROPS;
    }

    @PostMapping
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<String> addCrop(@RequestBody Crop crop) {
        CROPS.add(crop);
        return ResponseEntity.ok("Crop added with ID: " + crop.id());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('FARMER')")
    public ResponseEntity<Crop> getCropById(@PathVariable String id) {
        return CROPS.stream()
            .filter(crop -> crop.id().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}

