package com.restaurant.pricing;

import com.restaurant.domain.Order;

// no discount

public class NoDiscount implements DiscountStrategy {
    @Override
    public DiscountResult calculateDiscount(Order order) {
        return new DiscountResult("No Discount", 0.0);
    }
}
