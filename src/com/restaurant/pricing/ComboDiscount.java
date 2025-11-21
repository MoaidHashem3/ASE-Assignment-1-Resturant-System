package com.restaurant.pricing;

import com.restaurant.domain.Order;


// combo discount: if order has at least 2 items give a discount.

public class ComboDiscount implements DiscountStrategy {
    private final double amount;

    public ComboDiscount(double flatAmount) {
        this.amount = flatAmount;
    }

    @Override
    public DiscountResult calculateDiscount(Order order) {
        if (order.getItems().size() >= 2) {
            return new DiscountResult("Combo Discount", amount);
        }
        return new DiscountResult("Combo Discount", 0.0);
    }

}
