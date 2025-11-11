package com.restaurant.decorators;

import com.restaurant.domain.MenuItem;

public class Mushroom extends AddOnDecorator {
    public Mushroom(MenuItem menuItem) {
        super(menuItem);
    }

    @Override
    public double getPrice() {
        return super.getPrice() + 12.0;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", Mushroom";
    }
}

