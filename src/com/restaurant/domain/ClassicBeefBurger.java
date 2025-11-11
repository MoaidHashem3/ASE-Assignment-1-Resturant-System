package com.restaurant.domain;

public class ClassicBeefBurger implements MenuItem {
    @Override
    public String getDescription() {
        return "Classic Beef Burger";
    }

    @Override
    public double getPrice() {
        return 85.0;
    }
}

