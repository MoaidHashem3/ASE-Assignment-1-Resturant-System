package com.restaurant.domain;

public class KidsMiniBurger implements MenuItem {
    @Override
    public String getDescription() {
        return "Kids Mini Burger";
    }

    @Override
    public double getPrice() {
        return 55.0; // smaller portion
    }
}

