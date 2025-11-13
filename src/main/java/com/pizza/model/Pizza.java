package com.pizza.model;

import java.util.ArrayList;
import java.util.List;

public class Pizza {

    private String size; // Small / Medium / Large
    private List<Topping> toppings = new ArrayList<>();

    public Pizza(String size) {
        this.size = size;
    }

    // Getters
    public String getSize() {
        return size;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    // Setters
    public void setSize(String size) {
        this.size = size;
    }

    // Business Logic
    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public boolean removeTopping(String toppingName) {
        return toppings.removeIf(t -> t.getName().equalsIgnoreCase(toppingName));
    }

    public double calculatePrice() {
        double basePrice = switch (size.toLowerCase()) {
            case "small" -> 8.00;
            case "medium" -> 10.00;
            case "large" -> 12.00;
            default -> 10.00; // fallback
        };

        double toppingsTotal = toppings.stream()
                .mapToDouble(Topping::getPrice)
                .sum();

        return basePrice + toppingsTotal;
    }

    @Override
    public String toString() {
        return "Pizza (" + size + ") with " + toppings.size() + " toppings";
    }
}
