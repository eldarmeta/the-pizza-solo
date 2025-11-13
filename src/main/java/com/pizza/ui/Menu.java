package com.pizza.ui;

import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.Topping;
import com.pizza.model.Drink;
import com.pizza.model.SideItem;
import com.pizza.io.OrderPrinter;
import com.pizza.io.OrderFileWriter;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Order order = new Order();
    private Pizza pizza;
    private OrderPrinter printer = new OrderPrinter();
    private OrderFileWriter fileWriter = new OrderFileWriter();

    private final Topping[] AVAILABLE_TOPPINGS = {
            new Topping("Cheese", 1.00),
            new Topping("Pepperoni", 1.50),
            new Topping("Mushrooms", 0.75),
            new Topping("Onions", 0.50),
            new Topping("Sausage", 1.75)
    };

    private final Drink[] AVAILABLE_DRINKS = {
            new Drink("Coke", 2.00),
            new Drink("Sprite", 2.00),
            new Drink("Water", 1.00)
    };

    private final SideItem[] AVAILABLE_SIDES = {
            new SideItem("Garlic Knots", 3.50),
            new SideItem("Breadsticks", 3.00)
    };

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
                case 8 -> addDrinkToOrder();
                case 9 -> addSideToOrder();
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
        System.out.println("8. Add drink to order");
        System.out.println("9. Add side item to order");
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
        String size;

        while (true) {
            System.out.print("Enter pizza size (Small/Medium/Large): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("small") || input.equals("s")) {
                size = "Small";
                break;
            } else if (input.equals("medium") || input.equals("m")) {
                size = "Medium";
                break;
            } else if (input.equals("large") || input.equals("l")) {
                size = "Large";
                break;
            } else {
                System.out.println("Invalid size. Please enter Small, Medium, or Large (or S/M/L).");
            }
        }

        pizza = new Pizza(size);
        System.out.println("Created a new pizza of size: " + size);
    }

    private void addTopping() {
        if (pizza == null) {
            System.out.println("Please create a pizza first.");
            return;
        }

        System.out.println("Available toppings:");
        for (int i = 0; i < AVAILABLE_TOPPINGS.length; i++) {
            Topping t = AVAILABLE_TOPPINGS[i];
            System.out.println((i + 1) + ". " + t.getName() + " ($" + String.format("%.2f", t.getPrice()) + ")");
        }
        System.out.print("Choose topping number: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > AVAILABLE_TOPPINGS.length) {
                System.out.println("Invalid topping choice.");
                return;
            }
            Topping base = AVAILABLE_TOPPINGS[choice - 1];
            Topping topping = new Topping(base.getName(), base.getPrice());
            pizza.addTopping(topping);
            System.out.println("Added topping: " + topping.getName());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void removeTopping() {
        if (pizza == null || pizza.getToppings().isEmpty()) {
            System.out.println("No pizza or toppings found.");
            return;
        }
        System.out.print("Enter topping name to remove: ");
        String name = scanner.nextLine();
        boolean removed = pizza.getToppings().removeIf(t -> t.getName().equalsIgnoreCase(name));
        if (removed) {
            System.out.println("Removed topping: " + name);
        } else {
            System.out.println("Topping not found.");
        }
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
        if (order.getPizzas().isEmpty() && order.getDrinks().isEmpty() && order.getSides().isEmpty()) {
            System.out.println("No items in order.");
        } else {
            printer.printToConsole(order);
        }
    }

    private void saveOrderToFile() {
        if (order.getPizzas().isEmpty() && order.getDrinks().isEmpty() && order.getSides().isEmpty()) {
            System.out.println("No items to save.");
        } else {
            fileWriter.writeToFile(order);
        }
    }

    private void addDrinkToOrder() {
        System.out.println("Available drinks:");
        for (int i = 0; i < AVAILABLE_DRINKS.length; i++) {
            Drink d = AVAILABLE_DRINKS[i];
            System.out.println((i + 1) + ". " + d.getName() + " ($" + String.format("%.2f", d.getPrice()) + ")");
        }
        System.out.print("Choose drink number: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > AVAILABLE_DRINKS.length) {
                System.out.println("Invalid drink choice.");
                return;
            }
            Drink base = AVAILABLE_DRINKS[choice - 1];
            Drink drink = new Drink(base.getName(), base.getPrice());
            order.addDrink(drink);
            System.out.println("Added drink: " + drink.getName());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }

    private void addSideToOrder() {
        System.out.println("Available sides:");
        for (int i = 0; i < AVAILABLE_SIDES.length; i++) {
            SideItem s = AVAILABLE_SIDES[i];
            System.out.println((i + 1) + ". " + s.getName() + " ($" + String.format("%.2f", s.getPrice()) + ")");
        }
        System.out.print("Choose side number: ");

        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > AVAILABLE_SIDES.length) {
                System.out.println("Invalid side choice.");
                return;
            }
            SideItem base = AVAILABLE_SIDES[choice - 1];
            SideItem sideItem = new SideItem(base.getName(), base.getPrice());
            order.addSideItem(sideItem);
            System.out.println("Added side item: " + sideItem.getName());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input.");
        }
    }
}
