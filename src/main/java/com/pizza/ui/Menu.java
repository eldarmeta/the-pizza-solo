package com.pizza.ui;

import com.pizza.model.Pizza;
import com.pizza.model.Topping;

import java.util.Scanner;

public class Menu {
    private Scanner scanner = new Scanner(System.in);
    private Pizza pizza;

    public  void run() {
        System.out.println("*** Welcome to the Pizza App ***");

        while (true) {
            printMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> createPizza();
                case 2 -> addTopping();
                case 3 -> removeTopping();
                case 4 -> showPizza();
                case 5 -> {
                    completeOrder();
                    return;
                }
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
        System.out.println("2. Add topping");
        System.out.println("3. Remove topping");
        System.out.println("4. Show current pizza");
        System.out.println("5. Complete order");
        System.out.println("0. Exit");
        System.out.println("Choose an option: ");
    }
    private int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            return  -1;
        }
    }
    private void createPizza() {
        System.out.print("Enter pizza size (Small/Medium/Large): ");
        String size = scanner.nextLine();
        pizza = new Pizza(size);
        System.out.println("Created a new pizza of size: " + size);
    }
    private  void  addTopping() {
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
        if (pizza == null || pizza.getToppings().isEmpty()){
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
    private  void completeOrder() {
        if (pizza != null) {
            System.out.println("Your final order:");
            pizza.printInfo();
        }
        System.out.println("Thank you for  ordering!");
    }
}
