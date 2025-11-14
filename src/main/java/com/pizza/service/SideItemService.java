package com.pizza.service;

import com.pizza.model.SideItem;

import java.util.ArrayList;
import java.util.List;

public class SideItemService {

    private final List<SideItem> availableSides = new ArrayList<>();

    public SideItemService() {
        loadDefaultSides();
    }

    private void loadDefaultSides() {
        availableSides.add(new SideItem("Garlic Knots", 3.50));
        availableSides.add(new SideItem("Breadsticks", 4.00));
        availableSides.add(new SideItem("Cheesy Bread", 4.50));
        availableSides.add(new SideItem("Chicken Wings", 6.00));
        availableSides.add(new SideItem("Salad", 3.00));
    }

    public SideItem getSideByIndex(int index) {
        if (index < 0 || index >= availableSides.size()) {
            return null;
        }
        return availableSides.get(index);
    }

    public void printSidesMenu() {
        System.out.println("Available Side Items:");
        for (int i = 0; i < availableSides.size(); i++) {
            SideItem sideItem = availableSides.get(i);
            System.out.println((i + 1) + ". " + sideItem.getName() + " ($" + sideItem.getPrice() + ")");
        }
    }
}
