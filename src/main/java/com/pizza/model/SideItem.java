package com.pizza.model;

public class SideItem extends MenuItem {

    public SideItem(String name, double price) {
        super(name, price);
    }

    @Override
    public String toString() {
        return getName() + " ($" + String.format("%.2f", getPrice()) + ")";
    }
}
