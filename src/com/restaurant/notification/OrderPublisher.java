package com.restaurant.notification;

import com.restaurant.domain.Order;
import java.util.ArrayList;
import java.util.List;


public class OrderPublisher {

    private final List<OrderObserver> observers = new ArrayList<>();

    public void registerObserver(OrderObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void publishOrderPlaced(Order order) {
        System.out.println("Notifying all observers about new order: " + order.getId());
        for (OrderObserver observer : observers) {
            observer.update(order);
        }
    }
}
