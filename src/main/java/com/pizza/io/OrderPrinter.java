package com.pizza.io;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;
import com.pizza.model.Drink;
import com.pizza.model.SideItem;

import java.time.format.DateTimeFormatter;

public class OrderPrinter {

    public void printToConsole(Order order) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("=== Order Receipt ===");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Created at: " + order.getCreatedAt().format(formatter));
        System.out.println();

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

        if (!order.getDrinks().isEmpty()) {
            System.out.println("Drinks:");
            for (Drink drink : order.getDrinks()) {
                System.out.println("- " + drink.getName() + " ($" + String.format("%.2f", drink.getPrice()) + ")");
            }
            System.out.println();
        }

        if (!order.getSides().isEmpty()) {
            System.out.println("Sides:");
            for (SideItem sideItem : order.getSides()) {
                System.out.println("- " + sideItem.getName() + " ($" + String.format("%.2f", sideItem.getPrice()) + ")");
            }
            System.out.println();
        }

        System.out.println("TOTAL ORDER: $" + String.format("%.2f", order.getTotal()));
        System.out.println("Thank you for ordering!");
    }
}
