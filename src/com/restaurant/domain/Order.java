package com.restaurant.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class Order {

    private static int counter = 1;
    public enum OrderStatus {
        CREATED,
        PLACED,
        PAID,
        CANCELLED
    }

    private final int id;
    private final List<MenuItem> items = new ArrayList<>();
    private final Instant createdAt;
    private Instant placedAt;
    private OrderStatus status;
    private double discountApplied;
    private String paymentMethod;

    public Order() {
        this.id = counter++;
        this.createdAt = Instant.now();
        this.status = OrderStatus.CREATED;
        this.discountApplied = 0.0;
        this.paymentMethod = "UNPAID";
    }

    public void addItem(MenuItem item) {
        Objects.requireNonNull(item, "item must not be null");
        items.add(item);
    }

    public boolean removeItem(MenuItem item) {
        if (item == null) return false;
        return items.remove(item);
    }

    public void clearItems() {
        items.clear();
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getItemsCount() {
        return items.size();
    }


    public double getSubtotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public double getTotalAfterDiscount() {
        return Math.max(0.0, getSubtotal() - discountApplied);
    }

    public String getId() {
        return String.valueOf(id);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getPlacedAt() {
        return placedAt;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void markPlaced() {
        if (this.status == OrderStatus.CREATED) {
            this.status = OrderStatus.PLACED;
            this.placedAt = Instant.now();
        }
    }


    public void markPaid(String paymentMethod) {
        this.status = OrderStatus.PAID;
        this.paymentMethod = paymentMethod != null ? paymentMethod : this.paymentMethod;
    }

    public void markCancelled() {
        this.status = OrderStatus.CANCELLED;
    }

    public double getDiscountApplied() {
        return discountApplied;
    }


    public void setDiscountApplied(double discountApplied) {
        this.discountApplied = Math.max(0.0, discountApplied);
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return String.format(
                "Order{id=%s, status=%s, items=%d, subtotal=%.2f, discount=%.2f, totalAfterDiscount=%.2f, paymentMethod=%s}",
                id, status, getItemsCount(), getSubtotal(), discountApplied, getTotalAfterDiscount(), paymentMethod
        );
    }
}
