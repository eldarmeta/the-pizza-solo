package com.pizza.ui;

import com.pizza.io.OrderFileWriter;
import com.pizza.io.OrderPrinter;
import com.pizza.io.ReceiptWriter;
import com.pizza.model.Drink;
import com.pizza.model.Pizza;
import com.pizza.model.SideItem;
import com.pizza.model.Topping;
import com.pizza.service.DrinkService;
import com.pizza.service.OrderService;
import com.pizza.service.PizzaService;
import com.pizza.service.SideItemService;
import com.pizza.service.ToppingService;
import com.pizza.util.InputValidator;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final OrderService orderService = new OrderService();
    private final PizzaService pizzaService = new PizzaService();
    private final ToppingService toppingService = new ToppingService();
    private final DrinkService drinkService = new DrinkService();
    private final SideItemService sideItemService = new SideItemService();

    private final ReceiptWriter consoleWriter = new OrderPrinter();
    private final ReceiptWriter fileWriter = new OrderFileWriter();

    private Pizza currentPizza;

    public void start() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String input = scanner.nextLine();
            int choice = InputValidator.parseIntOrMinusOne(input);

            switch (choice) {
                case 1 -> createNewPizza();
                case 2 -> createSignaturePizza();
                case 3 -> addDrink();
                case 4 -> addSideItem();
                case 5 -> showCurrentOrder();
                case 6 -> saveOrderToFile();
                case 7 -> finalizeOrderAndStartNew();
                case 0 -> {
                    System.out.println("Exiting application. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("^^^^ PIZZA ORDERING SYSTEM ^^^^^");
        System.out.println("1. Create / Edit Pizza");
        System.out.println("2. Create Signature Pizza");
        System.out.println("3. Add Drink");
        System.out.println("4. Add Side Item");
        System.out.println("5. Show Current Order");
        System.out.println("6. Save Order to File");
        System.out.println("7. Finalize Order and Start New");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private void createNewPizza() {
        System.out.println();
        System.out.println("^^^^^^ CREATE / EDIT PIZZA ^^^^^^^");

        String size;
        while (true) {
            System.out.print("Enter pizza size (Small/Medium/Large or S/M/L): ");
            String input = scanner.nextLine();
            String normalized = InputValidator.normalizeSize(input);

            if (normalized == null) {
                System.out.println("Invalid size. Please enter Small, Medium, or Large (or S/M/L).");
            } else {
                size = normalized;
                break;
            }
        }

        currentPizza = pizzaService.createPizza(size);
        System.out.println("Created a new pizza of size: " + size);

        boolean editingToppings = true;
        while (editingToppings) {
            System.out.println();
            System.out.println("1. Add Topping");
            System.out.println("2. Remove Topping");
            System.out.println("3. Finish Pizza");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            int choice = InputValidator.parseIntOrMinusOne(input);

            switch (choice) {
                case 1 -> addToppingToCurrentPizza();
                case 2 -> removeToppingFromCurrentPizza();
                case 3 -> {
                    if (currentPizza != null) {
                        orderService.addPizza(currentPizza);
                        System.out.println("Pizza added to order.");
                        currentPizza = null;
                    } else {
                        System.out.println("No pizza to add.");
                    }
                    editingToppings = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createSignaturePizza() {
        System.out.println();
        System.out.println("### SIGNATURE PIZZAS ###");
        System.out.println("1. Margherita");
        System.out.println("2. Pepperoni");
        System.out.println("3. Veggie");
        System.out.print("Choose a signature pizza (or 0 to cancel): ");

        String input = scanner.nextLine();
        int choice = InputValidator.parseIntOrMinusOne(input);

        if (choice == 0) {
            System.out.println("Cancelled.");
            return;
        }

        System.out.print("Enter size (Small/Medium/Large or S/M/L): ");
        String sizeInput = scanner.nextLine();
        String size = InputValidator.normalizeSize(sizeInput);

        if (size == null) {
            System.out.println("Invalid size.");
            return;
        }

        Pizza pizza = pizzaService.createSignaturePizza(choice, size);

        if (pizza == null) {
            System.out.println("Invalid signature pizza option.");
            return;
        }

        orderService.addPizza(pizza);
        System.out.println("Signature pizza added to order!");
    }

    private void addToppingToCurrentPizza() {
        if (currentPizza == null) {
            System.out.println("No active pizza. Please create a pizza first.");
            return;
        }

        System.out.println();
        toppingService.printToppingsMenu();
        System.out.print("Choose topping number (or 0 to cancel): ");
        String input = scanner.nextLine();
        int choice = InputValidator.parseIntOrMinusOne(input);

        if (choice == 0) {
            System.out.println("Cancelled topping selection.");
            return;
        }

        Topping selected = toppingService.getToppingByIndex(choice - 1);
        if (selected == null) {
            System.out.println("Invalid topping number.");
        } else {
            pizzaService.addTopping(currentPizza, selected);
            System.out.println("Added topping: " + selected.getName());
        }
    }

    private void removeToppingFromCurrentPizza() {
        if (currentPizza == null) {
            System.out.println("No active pizza. Please create a pizza first.");
            return;
        }

        if (currentPizza.getToppings().isEmpty()) {
            System.out.println("This pizza has no toppings.");
            return;
        }

        System.out.println("Current toppings:");
        for (int i = 0; i < currentPizza.getToppings().size(); i++) {
            Topping t = currentPizza.getToppings().get(i);
            System.out.println((i + 1) + ". " + t.getName());
        }

        System.out.print("Enter topping name to remove (or leave empty to cancel): ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Cancelled.");
            return;
        }

        boolean removed = pizzaService.removeToppingByName(currentPizza, name);
        if (removed) {
            System.out.println("Removed topping: " + name);
        } else {
            System.out.println("Topping not found: " + name);
        }
    }

    private void addDrink() {
        System.out.println();
        drinkService.printDrinkMenu();
        System.out.print("Choose drink number (or 0 to cancel): ");
        String input = scanner.nextLine();
        int choice = InputValidator.parseIntOrMinusOne(input);

        if (choice == 0) {
            System.out.println("Cancelled drink selection.");
            return;
        }

        Drink drink = drinkService.getDrinkByIndex(choice - 1);
        if (drink == null) {
            System.out.println("Invalid drink number.");
        } else {
            orderService.addDrink(drink);
            System.out.println("Added drink: " + drink.getName());
        }
    }

    private void addSideItem() {
        System.out.println();
        sideItemService.printSidesMenu();
        System.out.print("Choose side item number (or 0 to cancel): ");
        String input = scanner.nextLine();
        int choice = InputValidator.parseIntOrMinusOne(input);

        if (choice == 0) {
            System.out.println("Cancelled side item selection.");
            return;
        }

        SideItem sideItem = sideItemService.getSideByIndex(choice - 1);
        if (sideItem == null) {
            System.out.println("Invalid side item number.");
        } else {
            orderService.addSideItem(sideItem);
            System.out.println("Added side item: " + sideItem.getName());
        }
    }

    private void showCurrentOrder() {
        System.out.println();
        if (!orderService.hasItems()) {
            System.out.println("Current order is empty.");
            return;
        }
        consoleWriter.write(orderService.getCurrentOrder());
    }

    private void saveOrderToFile() {
        System.out.println();
        if (!orderService.hasItems()) {
            System.out.println("No items in order to save.");
            return;
        }
        fileWriter.write(orderService.getCurrentOrder());
    }

    private void finalizeOrderAndStartNew() {
        System.out.println();
        if (!orderService.hasItems()) {
            System.out.println("Order is empty. Nothing to finalize.");
            return;
        }

        System.out.println("Final order:");
        consoleWriter.write(orderService.getCurrentOrder());

        double subtotal = orderService.getSubtotal();
        double tax = orderService.getTax();

        System.out.println("Subtotal: $" + String.format("%.2f", subtotal));
        System.out.println("Tax (7%): $" + String.format("%.2f", tax));

        System.out.print("Enter tip amount (leave empty for 0): ");
        String tipInput = scanner.nextLine().trim();
        double tip = 0.0;
        if (!tipInput.isEmpty()) {
            try {
                tip = Double.parseDouble(tipInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid tip. Using 0.");
                tip = 0.0;
            }
        }

        orderService.getCurrentOrder().setTip(tip);

        double finalTotal = orderService.getFinalTotal();

        System.out.println("Tip: $" + String.format("%.2f", tip));
        System.out.println("FINAL TOTAL: $" + String.format("%.2f", finalTotal));

        System.out.print("Do you want to save this order to file? (y/n): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("y") || input.equals("yes")) {
            fileWriter.write(orderService.getCurrentOrder());
        }

        orderService.startNewOrder();
        currentPizza = null;
        System.out.println("Started a new order.");
    }
}
