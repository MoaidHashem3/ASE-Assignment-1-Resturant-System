package com.restaurant.domain;

import com.restaurant.notification.OrderObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final List<MenuItem> items = new ArrayList<>();
    private final List<OrderObserver> observers = new ArrayList<>();

    // Methods for managing items and observers
    public void addItem(MenuItem item) {
        if (item == null) return;
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getItemsCount() {
        return items.size();
    }

    public double getTotalPrice() {
        double sum = 0.0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public void addObserver(OrderObserver observer) {
        if (observer == null) return;
        observers.add(observer);
    }

    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    public void placeOrder() {
        // any additional business logic can be placed here
        notifyObservers();
    }
}
