package com.restaurant.billing;

import com.restaurant.pricing.DiscountResult;
import java.util.List;


public class Bill {
    private final String orderId;
    private final double subtotal;
    private final List<DiscountResult> discountsApplied;
    private final double tax;
    private final double total;

    public Bill(String orderId, double subtotal, List<DiscountResult> discountsApplied, double tax, double total) {
        this.orderId = orderId;
        this.subtotal = subtotal;
        this.discountsApplied = discountsApplied;
        this.tax = tax;
        this.total = total;
    }

    public String getOrderId() { return orderId; }
    public double getSubtotal() { return subtotal; }
    public List<DiscountResult> getDiscountsApplied() { return discountsApplied; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bill for Order ").append(orderId).append("\n");
        sb.append(String.format("Subtotal: %.2f%n", subtotal));

        sb.append("Discounts:\n");
        if (discountsApplied.isEmpty()) {
            sb.append("  None\n");
        } else {
            for (DiscountResult dr : discountsApplied) {
                sb.append(String.format("  - %s: -%.2f%n", dr.getName(), dr.getAmount()));
            }
        }

        sb.append(String.format("Tax: +%.2f%n", tax));
        sb.append(String.format("Total: %.2f%n", total));
        return sb.toString();
    }
}
