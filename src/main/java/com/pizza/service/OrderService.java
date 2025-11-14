package com.pizza.service;

import com.pizza.model.Drink;
import com.pizza.model.Order;
import com.pizza.model.Pizza;
import com.pizza.model.SideItem;

public class OrderService {

    private Order currentOrder;

    public OrderService() {
        this.currentOrder = new Order();
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void startNewOrder() {
        this.currentOrder = new Order();
    }

    public void addPizza(Pizza pizza) {
        currentOrder.addPizza(pizza);
    }

    public void addDrink(Drink drink) {
        currentOrder.addDrink(drink);
    }

    public void addSideItem(SideItem sideItem) {
        currentOrder.addSideItem(sideItem);
    }

    public double getSubtotal() {
        return getCurrentOrder().getTotal();
    }

    public double getTax() {
        return getSubtotal() * 0.07;
    }

    public double getTotalWithTaxAndTip(double tip) {
        return getSubtotal() + getTax() + tip;
    }

    public boolean hasItems() {
        return !currentOrder.getPizzas().isEmpty()
                || !currentOrder.getDrinks().isEmpty()
                || !currentOrder.getSides().isEmpty();
    }
}
