package com.pizza.io;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;
import com.pizza.model.Drink;
import com.pizza.model.SideItem;
import com.pizza.util.DateUtil;
import com.pizza.util.PriceFormatter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderFileWriter implements ReceiptWriter {

    @Override
    public void write(Order order) {
        String timestamp = DateUtil.formatForFile(order.getCreatedAt());

        File receiptsDir = new File("receipts");
        if (!receiptsDir.exists()) {
            receiptsDir.mkdirs();
        }

        File file = new File(receiptsDir, timestamp + ".txt");
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileWriter(file));

            writer.println("#$#$# Order Receipt #$#$#");
            writer.println("Order ID: " + order.getOrderId());
            writer.println("Created at: " + DateUtil.formatForDisplay(order.getCreatedAt()));
            writer.println();

            int pizzaNumber = 1;
            for (Pizza pizza : order.getPizzas()) {
                writer.println("Pizza " + pizzaNumber + ":");
                writer.println("Size: " + pizza.getSize());
                writer.println("Toppings:");
                for (Topping topping : pizza.getToppings()) {
                    writer.println("- " + topping.getName() + " (" + PriceFormatter.format(topping.getPrice()) + ")");
                }
                writer.println("Pizza Total: " + PriceFormatter.format(pizza.calculatePrice()));
                writer.println();
                pizzaNumber++;
            }

            if (!order.getDrinks().isEmpty()) {
                writer.println("Drinks:");
                for (Drink drink : order.getDrinks()) {
                    writer.println("- " + drink.getName() + " (" + PriceFormatter.format(drink.getPrice()) + ")");
                }
                writer.println();
            }

            if (!order.getSides().isEmpty()) {
                writer.println("Sides:");
                for (SideItem sideItem : order.getSides()) {
                    writer.println("- " + sideItem.getName() + " (" + PriceFormatter.format(sideItem.getPrice()) + ")");
                }
                writer.println();
            }

            writer.println("TOTAL ORDER: " + PriceFormatter.format(order.getTotal()));
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
