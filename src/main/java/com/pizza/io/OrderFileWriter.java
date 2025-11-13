package com.pizza.io;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class OrderFileWriter {

    public void writeToFile(Order order, String filename) {
        PrintWriter writer = null;

        try {
            FileWriter fileWriter = new FileWriter(filename);
            writer = new PrintWriter(fileWriter);

            writer.println("=== Order Receipt ===");

            int pizzaNumber = 1;
            for (Pizza pizza : order.getPizzas()) {
                writer.println("Pizza " + pizzaNumber + ":");
                writer.println("Size: " + pizza.getSize());
                writer.println("Toppings:");
                for (Topping topping : pizza.getToppings()) {
                    writer.println("- " + topping.getName() + " ($" + String.format("%.2f", topping.getPrice()) + ")");
                }
                writer.println("Pizza Total: $" + String.format("%.2f", pizza.calculatePrice()));
                writer.println();
                pizzaNumber++;
            }

            writer.println("TOTAL ORDER: $" + String.format("%.2f", order.getTotal()));
            writer.println("Thank you for ordering!");
            System.out.println("✅ Order successfully saved to file: " + filename);

        } catch (IOException e) {
            System.out.println("⚠️ Error saving order: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
