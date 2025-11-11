package com.restaurant.decorators;

import com.restaurant.domain.MenuItem;

public class ExtraCheese extends AddOnDecorator {
    public ExtraCheese(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 10.0; // price for extra cheese
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Extra Cheese";
    }
}

