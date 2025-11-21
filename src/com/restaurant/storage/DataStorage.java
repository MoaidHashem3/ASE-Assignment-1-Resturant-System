// src/com/restaurant/storage/InMemoryStore.java
package com.restaurant.storage;

import com.restaurant.structure.MenuComponent;
import com.restaurant.domain.Order;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {
    private final Map<String, MenuComponent> menus = new HashMap<>();
    private final Map<String, Order> orders = new HashMap<>();

    public void saveMenu(String name, MenuComponent menu) { menus.put(name, menu); }
    public MenuComponent getMenu(String name) { return menus.get(name); }

    public void saveOrder(Order order) { orders.put(order.getId(), order); }
    public Order getOrder(String id) { return orders.get(id); }
}
