package com.restaurant.factory;

import com.restaurant.domain.MenuItem;

public interface MenuFactory {
    MenuItem createPizza();
    MenuItem createBurger();
}

