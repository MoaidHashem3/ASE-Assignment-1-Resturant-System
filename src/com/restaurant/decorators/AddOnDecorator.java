package com.restaurant.decorators;

import com.restaurant.domain.MenuItem;

public abstract class AddOnDecorator implements MenuItem {
    protected final MenuItem menuItem;

    public AddOnDecorator(MenuItem menuItem) {this.menuItem = menuItem;}

    @Override
    public String getDescription() {return menuItem.getDescription(); }

    @Override
    public double getPrice() {
        return menuItem.getPrice();
    }
}
