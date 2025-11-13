package com.pizza.io;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;
import com.pizza.model.Drink;
import com.pizza.model.SideItem;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class OrderFileWriter {

    public void writeToFile(Order order) {
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String timestamp = order.getCreatedAt().format(fileFormatter);

        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs();
        }

        File file = new File(receiptsDir, timestamp + ".txt");

        PrintWriter writer = null;

        try {
            FileWriter fileWriter = new FileWriter(file);
            writer = new PrintWriter(fileWriter);

            DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            writer.println("=== Order Receipt ===");
            writer.println("Order ID: " + order.getOrderId());
            writer.println("Created at: " + order.getCreatedAt().format(displayFormatter));
            writer.println();

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

            if (!order.getDrinks().isEmpty()) {
                writer.println("Drinks:");
                for (Drink drink : order.getDrinks()) {
                    writer.println("- " + drink.getName() + " ($" + String.format("%.2f", drink.getPrice()) + ")");
                }
                writer.println();
            }

            if (!order.getSides().isEmpty()) {
                writer.println("Sides:");
                for (SideItem sideItem : order.getSides()) {
                    writer.println("- " + sideItem.getName() + " ($" + String.format("%.2f", sideItem.getPrice()) + ")");
                }
                writer.println();
            }

            writer.println("TOTAL ORDER: $" + String.format("%.2f", order.getTotal()));
            writer.println("Thank you for ordering!");
            System.out.println("Order successfully saved to file: " + file.getPath());

        } catch (IOException e) {
            System.out.println("Error saving order: " + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
