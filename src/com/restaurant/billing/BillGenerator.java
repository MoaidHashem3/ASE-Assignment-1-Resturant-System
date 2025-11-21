package com.restaurant.billing;

import com.restaurant.domain.Order;
import com.restaurant.pricing.DiscountChain;

public class BillGenerator {
    private final DiscountChain discountChain;
    private final double taxRate;

    public BillGenerator(DiscountChain discountChain, double taxRate) {
        this.discountChain = discountChain;
        this.taxRate = taxRate;
    }

    public Bill generate(Order order) {
        double subtotal = order.getSubtotal();
        double discounts = discountChain.applyAll(order);
        double taxable = Math.max(0.0, subtotal - discounts);
        double tax = taxable * taxRate;
        double total = taxable + tax;
        return new Bill(order.getId(), subtotal, discounts, tax, total);
    }
}
