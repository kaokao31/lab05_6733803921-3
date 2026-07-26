package com.example.lab5;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coffees")
public class CoffeeController {

    private final CoffeeService coffeeService;

    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public List<Coffee> getAll() {
        return coffeeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getById(@PathVariable Long id) {
        Coffee coffee = coffeeService.getById(id);
        if (coffee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(coffee);
    }

    @PostMapping
    public ResponseEntity<Coffee> create(@RequestBody Coffee coffee) {
        Coffee created = coffeeService.create(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> update(@PathVariable Long id, @RequestBody Coffee coffee) {
        Coffee updated = coffeeService.update(id, coffee);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = coffeeService.delete(id);
        if (!removed) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}