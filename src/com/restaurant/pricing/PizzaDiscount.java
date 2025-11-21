package com.restaurant.pricing;

import com.restaurant.domain.Order;

//Pizza discount, any rate %
public class PizzaDiscount implements DiscountStrategy {
    private final double rate;
    public PizzaDiscount(double rate) {
        this.rate = rate;
    }
    @Override
    public DiscountResult calculateDiscount(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            return new DiscountResult("Pizza " + (int)(rate * 100) + "% Off", 0.0);
        }
        double discount = order.getItems().stream()
                .filter(i -> {
                    String desc = i.getDescription();
                    return desc != null && desc.toLowerCase().contains("pizza");
                })
                .mapToDouble(i -> i.getPrice() * rate)
                .sum();
        return new DiscountResult("Pizza " + (int)(rate * 100) + "% Off", discount);
    }
}
