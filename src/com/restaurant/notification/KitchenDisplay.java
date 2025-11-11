package com.restaurant.notification;

import com.restaurant.domain.Order;

public class KitchenDisplay implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("KITCHEN: New order received with " + order.getItemsCount() + " item(s).");
    }
}

