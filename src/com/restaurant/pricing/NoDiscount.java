package com.restaurant.pricing;

import com.restaurant.domain.Order;

// Default: no discount.

public class NoDiscount implements DiscountStrategy {
    @Override
    public double calculateDiscount(Order order) {
        return 0.0;
    }
}
