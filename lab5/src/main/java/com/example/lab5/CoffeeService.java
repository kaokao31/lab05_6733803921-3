package com.example.lab5;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CoffeeService {

    private final List<Coffee> coffees = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong();

    public CoffeeService() {
        coffees.add(new Coffee(idCounter.incrementAndGet(), "Espresso", 45.0));
        coffees.add(new Coffee(idCounter.incrementAndGet(), "Latte", 55.0));
    }

    public List<Coffee> getAll() {
        return coffees;
    }

    public Coffee getById(Long id) {
        return coffees.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Coffee create(Coffee coffee) {
        coffee.setId(idCounter.incrementAndGet());
        coffees.add(coffee);
        return coffee;
    }

    public Coffee update(Long id, Coffee updated) {
        Coffee existing = getById(id);
        if (existing == null) {
            return null;
        }
        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        return existing;
    }

    public boolean delete(Long id) {
        return coffees.removeIf(c -> c.getId().equals(id));
    }
}