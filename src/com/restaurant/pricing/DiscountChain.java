package com.restaurant.pricing;

import com.restaurant.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class DiscountChain {
    private final List<DiscountStrategy> strategies = new ArrayList<>();

    public void addStrategy(DiscountStrategy s) {
        strategies.add(s);
    }

    public double applyAll(Order order) {
        double totalDiscount = 0.0;
        for (DiscountStrategy s : strategies) {
            double d = s.calculateDiscount(order);
            if (d > 0) {
                totalDiscount += d;
            }
        }
        order.setDiscountApplied(totalDiscount);
        return totalDiscount;
    }
}
