package com.restaurant.payment;

import com.restaurant.domain.Order;


//cash payment: always succeeds

public class CashPayment implements PaymentStrategy {
    @Override
    public PaymentResult pay(Order order, double amount) {
        order.setPaymentMethod("CASH");
        return new PaymentResult(true, "Paid in cash", "CASH");
    }
}
