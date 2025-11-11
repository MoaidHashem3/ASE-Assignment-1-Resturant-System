package com.restaurant.factory;

import com.restaurant.domain.ClassicBeefBurger;
import com.restaurant.domain.MenuItem;
import com.restaurant.domain.PepperoniPizza;

public class NonVegetarianMenuFactory implements MenuFactory {
    @Override
    public MenuItem createPizza() {
        return new PepperoniPizza();
    }

    @Override
    public MenuItem createBurger() {
        return new ClassicBeefBurger();
    }
}

