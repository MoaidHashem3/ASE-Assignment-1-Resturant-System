package com.restaurant.domain;

public class VeggiePizza implements MenuItem {
    @Override
    public String getDescription() {
        return "Veggie Pizza";
    }

    @Override
    public double getPrice() {
        return 80.0;
    }
}

