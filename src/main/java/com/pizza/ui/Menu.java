package com.pizza.ui;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;
import com.pizza.io.OrderPrinter;
import com.pizza.io.OrderFileWriter;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Order order = new Order();
    private Pizza pizza;
    private OrderPrinter printer = new OrderPrinter();
    private OrderFileWriter fileWriter = new OrderFileWriter();

    public void run() {
        System.out.println("=== Welcome to The Pizza App ===");

        while (true) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> createPizza();
                case 2 -> addTopping();
                case 3 -> removeTopping();
                case 4 -> showPizza();
                case 5 -> savePizzaToOrder();
                case 6 -> showOrder();
                case 7 -> saveOrderToFile();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n1. Create new pizza");
        System.out.println("2. Add topping to current pizza");
        System.out.println("3. Remove topping from current pizza");
        System.out.println("4. Show current pizza");
        System.out.println("5. Save pizza to order");
        System.out.println("6. Show full order");
        System.out.println("7. Save order to file");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void createPizza() {
        System.out.print("Enter pizza size (Small/Medium/Large): ");
        String size = scanner.nextLine();
        pizza = new Pizza(size);
        System.out.println("Created a new pizza of size: " + size);
    }

    private void addTopping() {
        if (pizza == null) {
            System.out.println("Please create a pizza first.");
            return;
        }
        System.out.print("Enter topping name: ");
        String name = scanner.nextLine();
        System.out.print("Enter topping price: ");
        double price = Double.parseDouble(scanner.nextLine());
        pizza.addTopping(new Topping(name, price));
        System.out.println("Added topping: " + name);
    }

    private void removeTopping() {
        if (pizza == null || pizza.getToppings().isEmpty()) {
            System.out.println("No pizza or toppings found.");
            return;
        }
        System.out.print("Enter topping name to remove: ");
        String name = scanner.nextLine();
        pizza.getToppings().removeIf(t -> t.getName().equalsIgnoreCase(name));
        System.out.println("Removed topping: " + name);
    }

    private void showPizza() {
        if (pizza == null) {
            System.out.println("No pizza created yet.");
        } else {
            pizza.printInfo();
        }
    }

    private void savePizzaToOrder() {
        if (pizza == null) {
            System.out.println("No pizza to save.");
        } else {
            order.addPizza(pizza);
            System.out.println("Pizza added to order.");
            pizza = null;
        }
    }

    private void showOrder() {
        if (order.getPizzas().isEmpty()) {
            System.out.println("No pizzas in order.");
        } else {
            printer.printToConsole(order);
        }
    }

    private void saveOrderToFile() {
        if (order.getPizzas().isEmpty()) {
            System.out.println("No pizzas to save.");
        } else {
            System.out.print("Enter filename to save order (e.g. receipt.txt): ");
            String filename = scanner.nextLine();
            fileWriter.writeToFile(order, filename);
        }
    }
}
