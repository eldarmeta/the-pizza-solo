package com.pizza.service;

import com.pizza.model.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkService {

    private final List<Drink> availableDrinks = new ArrayList<>();

    public DrinkService() {
        loadDefaultDrinks();
    }

    private void loadDefaultDrinks() {
        availableDrinks.add(new Drink("Coke", 1.50));
        availableDrinks.add(new Drink("Sprite", 1.50));
        availableDrinks.add(new Drink("Fanta", 1.50));
        availableDrinks.add(new Drink("Water", 1.00));
        availableDrinks.add(new Drink("Iced Tea", 2.00));
    }

    public List<Drink> getAvailableDrinks() {
        return availableDrinks;
    }

    public Drink getDrinkByIndex(int index) {
        if (index < 0 || index >= availableDrinks.size()) {
            return null;
        }
        return availableDrinks.get(index);
    }

    public void printDrinkMenu() {
        System.out.println("Available Drinks:");
        for (int i = 0; i < availableDrinks.size(); i++) {
            Drink drink = availableDrinks.get(i);
            System.out.println((i + 1) + ". " + drink.getName() + " ($" + drink.getPrice() + ")");
        }
    }
}
