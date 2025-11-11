package com.restaurant.domain;

public class PepperoniPizza implements MenuItem {
    @Override
    public String getDescription() {
        return "Pepperoni Pizza";
    }

    @Override
    public double getPrice() {
        return 95.0;
    }
}

