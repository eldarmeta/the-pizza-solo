package com.pizza.model;

public class Topping extends MenuItem {

    public Topping(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return getName() + " ($" + String.format("%.2f", getPrice()) + ")";
    }
}
