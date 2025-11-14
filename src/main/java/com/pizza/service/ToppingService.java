package com.pizza.service;

import com.pizza.model.Topping;

import java.util.ArrayList;
import java.util.List;

public class ToppingService {

    private final List<Topping> availableToppings = new ArrayList<>();

    public ToppingService() {
        loadDefaultToppings();
    }

    private void loadDefaultToppings() {
        availableToppings.add(new Topping("Cheese", 1.00));
        availableToppings.add(new Topping("Pepperoni", 1.50));
        availableToppings.add(new Topping("Sausage", 1.75));
        availableToppings.add(new Topping("Mushrooms", 0.75));
        availableToppings.add(new Topping("Onions", 0.50));
        availableToppings.add(new Topping("Olives", 0.80));
    }

    public Topping getToppingByIndex(int index) {
        if (index < 0 || index >= availableToppings.size()) {
            return null;
        }
        return availableToppings.get(index);
    }

    public void printToppingsMenu() {
        System.out.println("Available Toppings:");
        for (int i = 0; i < availableToppings.size(); i++) {
            Topping topping = availableToppings.get(i);
            System.out.println((i + 1) + ". " + topping.getName() + " ($" + topping.getPrice() + ")");
        }
    }
}
