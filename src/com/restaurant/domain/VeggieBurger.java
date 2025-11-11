package com.restaurant.domain;

public class VeggieBurger implements MenuItem {
    @Override
    public String getDescription() {
        return "Veggie Burger";
    }

    @Override
    public double getPrice() {
        return 70.0;
    }
}

