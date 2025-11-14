package com.pizza.io;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;
import com.pizza.model.Drink;
import com.pizza.model.SideItem;
import com.pizza.util.DateUtil;
import com.pizza.util.PriceFormatter;

public class OrderPrinter implements ReceiptWriter {

    @Override
    public void write(Order order) {
        System.out.println("***** ORDER RECEIPT *****");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Created at: " + DateUtil.formatForDisplay(order.getCreatedAt()));
        System.out.println();

        int pizzaNumber = 1;
        for (Pizza pizza : order.getPizzas()) {
            System.out.println("Pizza " + pizzaNumber + ":");
            System.out.println("Size: " + pizza.getSize());
            System.out.println("Toppings:");
            for (Topping topping : pizza.getToppings()) {
                System.out.println("- " + topping.getName() + " (" + PriceFormatter.format(topping.getPrice()) + ")");
            }
            System.out.println("Pizza Total: " + PriceFormatter.format(pizza.calculatePrice()));
            System.out.println();
            pizzaNumber++;
        }

        if (!order.getDrinks().isEmpty()) {
            System.out.println("Drinks:");
            for (Drink drink : order.getDrinks()) {
                System.out.println("- " + drink.getName() + " (" + PriceFormatter.format(drink.getPrice()) + ")");
            }
            System.out.println();
        }

        if (!order.getSides().isEmpty()) {
            System.out.println("Sides:");
            for (SideItem sideItem : order.getSides()) {
                System.out.println("- " + sideItem.getName() + " (" + PriceFormatter.format(sideItem.getPrice()) + ")");
            }
            System.out.println();
        }

        double subtotal = order.getTotal();
        double tax = subtotal * 0.07;
        double tip = order.getTip();
        double finalTotal = subtotal + tax + tip;

        System.out.println("Subtotal: " + PriceFormatter.format(subtotal));
        System.out.println("Tax (7%): " + PriceFormatter.format(tax));
        System.out.println("Tip: " + PriceFormatter.format(tip));
        System.out.println("------------------------------");
        System.out.println("FINAL TOTAL: " + PriceFormatter.format(finalTotal));
        System.out.println("------------------------------");
    }
}
