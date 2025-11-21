package com.restaurant.pricing;

import com.restaurant.domain.Order;

public class PizzaDiscount implements DiscountStrategy {
    private final double rate;

    public PizzaDiscount(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateDiscount(Order order) {
        double discount = 0.0;
        return order.getItems().stream()
                .filter(i -> i.getDescription().toLowerCase().contains("pizza"))
                .mapToDouble(i -> i.getPrice() * rate)
                .sum();
    }
}
