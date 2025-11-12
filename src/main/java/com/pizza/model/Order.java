package com.pizza.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
        private List<Pizza> pizzas;

        public Order() {
            this.pizzas = new ArrayList<>();
        }
//
        public void addPizza(Pizza pizza) {
            pizzas.add(pizza);
        }

        public double getTotal() {
            double total = 0.0;
            for (Pizza pizza : pizzas) {
                total += pizza.calculatePrice();
            }
            return total;
            }

            public void printReceipt() {
                System.out.println("***** Order Receipt *****");
                int index = 1;
                for (Pizza pizza : pizzas) {
                    System.out.println("Pizza " + index + ":");
                    pizza.printInfo();
                    System.out.println();
                    index++;
                }
                System.out.println("TOTAL: $" + String.format("%.2f", getTotal()));
            }

    public List<Pizza> getPizzas() {
        return pizzas;
    }
}