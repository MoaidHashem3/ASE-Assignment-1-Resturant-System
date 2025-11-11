package com.restaurant.domain;

public class KidsCheesePizza implements MenuItem {
    @Override
    public String getDescription() {
        return "Kids Cheese Pizza";
    }

    @Override
    public double getPrice() {
        return 60.0; // smaller, cheaper portion
    }
}

