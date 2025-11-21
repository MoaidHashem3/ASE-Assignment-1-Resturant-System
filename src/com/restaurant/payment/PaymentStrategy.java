package com.restaurant.payment;

import com.restaurant.domain.Order;
public interface PaymentStrategy {
    PaymentResult pay(Order order, double amount);
}
