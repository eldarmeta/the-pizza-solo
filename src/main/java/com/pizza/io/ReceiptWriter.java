package com.pizza.io;

import com.pizza.model.Order;

public interface ReceiptWriter {
    void write(Order order);
}
