package com.restaurant.factory;

import com.restaurant.domain.KidsCheesePizza;
import com.restaurant.domain.KidsMiniBurger;
import com.restaurant.domain.MenuItem;

public class KidsMenuFactory implements MenuFactory {
    @Override
    public MenuItem createPizza() {
        return new KidsCheesePizza();
    }

    @Override
    public MenuItem createBurger() {
        return new KidsMiniBurger();
    }
}

