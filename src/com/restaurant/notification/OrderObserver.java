package com.restaurant.notification;

import com.restaurant.domain.Order;

public interface OrderObserver {
    void update(Order order);
}

