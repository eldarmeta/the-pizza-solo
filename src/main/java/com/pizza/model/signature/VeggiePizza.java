package com.pizza.model.signature;

import com.pizza.model.Pizza;
import com.pizza.model.Topping;

public class VeggiePizza extends Pizza {

    public VeggiePizza(String size) {
        super(size);
        addTopping(new Topping("Mushrooms", 0.75));
        addTopping(new Topping("Onions", 0.50));
        addTopping(new Topping("Olives", 0.80));
    }
}
