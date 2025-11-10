package com.pizza.model;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String size;
    private double basePrice;
    private List<Topping> toppings;

    public  Pizza(String size) {
        this.size = size;
        this.toppings = new ArrayList<>();
        setBasePriceBySize(size);
    }

    private void setBasePriceBySize(String size) {
        switch (size.toLowerCase()) {
            case "small":
                this.basePrice = 8.00;
                break;
            case "medium":
                this.basePrice = 10.00;
                break;
            case  "large":
                this.basePrice = 12.00;
                break;
            default:
                this.basePrice = 10.00;
                System.out.println("Unknown size, defaulting to medium.");
        }
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
    }

    public  void removeTopping(Topping topping) {
        toppings.remove(topping);
    }

    public double calculatePrice() {
        double total = basePrice;
        for (Topping topping : toppings) {
            total += topping.getPrice();
        }
        return total;
    }

    public void  printInfo() {
        System.out.println("Pizza size: " + size);
        System.out.println("Toppings: ");
        for (Topping topping : toppings) {
            System.out.println("- " + topping);
        }
        System.out.println("Total price: $" + String.format("%.2f", calculatePrice()));
    }

    public String getSize(){
        return size;
    }

    public  double getBasePrice() {
        return basePrice;
    }

    public List<Topping> getToppings() {
        return toppings;
    }
}
