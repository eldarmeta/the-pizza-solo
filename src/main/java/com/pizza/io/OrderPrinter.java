package com.pizza.io;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;

public class OrderPrinter {

    public void printToConsole(Order order) {
        System.out.println("=== Order Receipt ===");

        int pizzaNumber = 1;
        for (Pizza pizza : order.getPizzas()) {
            System.out.println("Pizza " + pizzaNumber + ":");
            System.out.println("Size: " + pizza.getSize());
            System.out.println("Toppings:");
            for (Topping topping : pizza.getToppings()) {
                System.out.println("- " + topping.getName() + " ($" + String.format("%.2f", topping.getPrice()) + ")");
            }
            System.out.println("Pizza Total: $" + String.format("%.2f", pizza.calculatePrice()));
            System.out.println();
            pizzaNumber++;
        }

        System.out.println("TOTAL ORDER: $" + String.format("%.2f", order.getTotal()));
        System.out.println("Thank you for ordering!");
    }
}
