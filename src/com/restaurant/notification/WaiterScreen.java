package com.restaurant.notification;

import com.restaurant.domain.Order;

public class WaiterScreen implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("WAITER: Order is placed with total " + order.getTotalPrice());
    }
}

