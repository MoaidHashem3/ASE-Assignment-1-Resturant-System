package com.restaurant.decorators;

import com.restaurant.domain.MenuItem;

public class SpicySauce extends AddOnDecorator {
    public SpicySauce(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 5.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Spicy Sauce";
    }
}

