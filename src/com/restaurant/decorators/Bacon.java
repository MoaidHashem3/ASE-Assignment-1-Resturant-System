package com.restaurant.decorators;

import com.restaurant.domain.MenuItem;

public class Bacon extends AddOnDecorator {
    public Bacon(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 15.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Bacon";
    }
}

