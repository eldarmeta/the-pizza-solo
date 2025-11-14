package com.pizza.service;

import com.pizza.model.Pizza;
import com.pizza.model.Topping;
import com.pizza.model.signature.MargheritaPizza;
import com.pizza.model.signature.PepperoniSignaturePizza;
import com.pizza.model.signature.VeggiePizza;

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

    public Pizza createSignaturePizza(int option, String size) {
        return switch (option) {
            case 1 -> new MargheritaPizza(size);
            case 2 -> new PepperoniSignaturePizza(size);
            case 3 -> new VeggiePizza(size);
            default -> null;
        };
    }

}
