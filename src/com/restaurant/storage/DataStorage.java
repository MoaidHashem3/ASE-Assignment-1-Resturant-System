package com.restaurant.storage;

import com.restaurant.structure.MenuComponent;
import com.restaurant.domain.Order;

import java.util.*;

public class DataStorage {

    private final Map<String, MenuComponent> menus = new HashMap<>();
    private final Map<String, Order> orders = new HashMap<>();

    public void saveMenu(String name, MenuComponent menu) {
        if (name == null || menu == null) return;
        menus.put(name, menu);
    }

    public MenuComponent getMenu(String name) {
        return menus.get(name);
    }

    public List<MenuComponent> getAllMenus() {
        return new ArrayList<>(menus.values());
    }

    public void saveOrder(Order order) {
        if (order == null) return;
        orders.put(order.getId(), order);
    }

    public Order getOrder(String id) {
        return orders.get(id);
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }
}
