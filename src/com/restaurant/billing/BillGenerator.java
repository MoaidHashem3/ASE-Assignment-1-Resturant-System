package com.restaurant.billing;

import com.restaurant.domain.Order;
import com.restaurant.pricing.DiscountResult;
import com.restaurant.pricing.DiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class BillGenerator {
    private final List<DiscountStrategy> strategies;
    private final double taxRate;

    public BillGenerator(List<DiscountStrategy> strategies, double taxRate) {
        this.strategies = strategies == null ? new ArrayList<>() : strategies;
        this.taxRate = taxRate;
    }

    public Bill generate(Order order) {
        double subtotal = order.getSubtotal();

        List<DiscountResult> results = new ArrayList<>();
        double totalDiscount = 0.0;
        for (DiscountStrategy s : strategies) {
            DiscountResult r = s.calculateDiscount(order);
            if (r == null) continue;
            results.add(r);
            totalDiscount += r.getAmount();
        }

        order.setDiscountApplied(totalDiscount);

        double taxable = Math.max(0.0, subtotal - totalDiscount);
        double tax = taxable * taxRate;
        double total = taxable + tax;

        return new Bill(order.getId(), subtotal, results, tax, total);
    }
}
