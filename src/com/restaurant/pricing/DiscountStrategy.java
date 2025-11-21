package com.restaurant.pricing;

import com.restaurant.domain.Order;
public interface DiscountStrategy {
    DiscountResult calculateDiscount(Order order);
}

