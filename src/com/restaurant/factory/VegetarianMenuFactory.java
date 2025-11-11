package com.restaurant.factory;

import com.restaurant.domain.MenuItem;
import com.restaurant.domain.VeggieBurger;
import com.restaurant.domain.VeggiePizza;

public class VegetarianMenuFactory implements MenuFactory {
    @Override
    public MenuItem createPizza() {
        return new VeggiePizza();
    }

    @Override
    public MenuItem createBurger() {
        return new VeggieBurger();
    }
}

