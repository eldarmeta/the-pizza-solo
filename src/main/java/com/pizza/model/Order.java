package com.pizza.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Pizza> pizzas;
    private List<Drink> drinks;
    private List<SideItem> sides;
    private String orderId;
    private LocalDateTime createdAt;

    public Order() {
        this.pizzas = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.sides = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        this.orderId = "ORD-" + this.createdAt.format(formatter);
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    public void addSideItem(SideItem sideItem) {
        sides.add(sideItem);
    }

    public double getTotal() {
        double total = 0.0;
        for (Pizza pizza : pizzas) {
            total += pizza.calculatePrice();
        }
        for (Drink drink : drinks) {
            total += drink.getPrice();
        }
        for (SideItem sideItem : sides) {
            total += sideItem.getPrice();
        }
        return total;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<Drink> getDrinks() {
        return drinks;
    }

    public List<SideItem> getSides() {
        return sides;
    }

    public String getOrderId() {
        return orderId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
