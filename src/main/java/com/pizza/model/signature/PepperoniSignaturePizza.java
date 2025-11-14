package com.pizza.model.signature;

import com.pizza.model.Pizza;
import com.pizza.model.Topping;

public class PepperoniSignaturePizza extends Pizza {

    public PepperoniSignaturePizza(String size) {
        super(size);
        addTopping(new Topping("Cheese", 1.00));
        addTopping(new Topping("Tomato Sauce", 0.75));
        addTopping(new Topping("Pepperoni", 1.50));
    }
}
