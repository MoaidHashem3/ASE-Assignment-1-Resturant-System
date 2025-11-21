package com.restaurant.pricing;

import com.restaurant.domain.Order;
public interface DiscountStrategy {

    double calculateDiscount(Order order);
}
