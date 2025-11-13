package com.pizza.model;

public class Drink extends MenuItem {

    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return getName() + " ($" + String.format("%.2f", getPrice()) + ")";
    }
}
