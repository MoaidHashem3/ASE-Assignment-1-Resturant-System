package com.restaurant.pricing;

public class DiscountResult {
    private final String name;
    private final double amount;

    public DiscountResult(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%s: -%.2f", name, amount);
    }
}
