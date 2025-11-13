package com.pizza.model;

public abstract class MenuItem {

    private String name;
    private double price;

    protected MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Every item must define how it prints itself
    @Override
    public String toString() {
        return name + " ($" + String.format("%.2f", price) + ")";
    }
}
