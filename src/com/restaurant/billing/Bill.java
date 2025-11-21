package com.restaurant.billing;

public class Bill {
    private final String orderId;
    private final double subtotal;
    private final double discounts;
    private final double tax;
    private final double total;

    public Bill(String orderId, double subtotal, double discounts, double tax, double total) {
        this.orderId = orderId;
        this.subtotal = subtotal;
        this.discounts = discounts;
        this.tax = tax;
        this.total = total;
    }

    public String getOrderId() { return orderId; }
    public double getSubtotal() { return subtotal; }
    public double getDiscounts() { return discounts; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        return String.format(
                "Bill for Order %s%nSubtotal: %.2f%nDiscounts: -%.2f%nTax: +%.2f%nTotal: %.2f%n",
                orderId, subtotal, discounts, tax, total
        );
    }
}
