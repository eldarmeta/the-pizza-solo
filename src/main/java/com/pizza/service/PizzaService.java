package com.pizza.service;

import com.pizza.model.Pizza;
import com.pizza.model.Topping;

public class PizzaService {

    public Pizza createPizza(String size) {
        return new Pizza(size);
    }

    public void addTopping(Pizza pizza, Topping topping) {
        pizza.addTopping(topping);
    }

    public boolean removeToppingByName(Pizza pizza, String toppingName) {
        return pizza.removeTopping(toppingName);
    }

    public double calculatePrice(Pizza pizza) {
        return pizza.calculatePrice();
    }
}
