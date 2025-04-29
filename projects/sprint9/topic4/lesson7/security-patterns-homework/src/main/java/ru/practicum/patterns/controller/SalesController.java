package ru.practicum.patterns.controller;

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
import ru.practicum.patterns.domain.Sale;

@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private static final List<Sale> SALES = new ArrayList<>();

    @GetMapping
    @PreAuthorize("hasRole('SELLER')")
    public List<Sale> getAllSales() {
        return SALES;
    }

    @PostMapping
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<String> createSale(@RequestBody Sale sale) {
        SALES.add(sale);
        return ResponseEntity.ok("Sale created with ID: " + sale.id());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SELLER')")
    public ResponseEntity<Sale> getSaleById(@PathVariable String id) {
        return SALES.stream()
            .filter(sale -> sale.id().equals(id))
            .findFirst()
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
}
