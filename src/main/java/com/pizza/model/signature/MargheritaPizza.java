package com.pizza.model.signature;

import com.pizza.model.Pizza;
import com.pizza.model.Topping;

public class MargheritaPizza extends Pizza {

    public MargheritaPizza(String size) {
        super(size);
        addTopping(new Topping("Cheese", 1.00));
        addTopping(new Topping("Tomato Sauce", 0.75));
        addTopping(new Topping("Basil", 0.50));
    }
}
