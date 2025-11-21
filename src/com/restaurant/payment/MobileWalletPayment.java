package com.restaurant.payment;

import com.restaurant.domain.Order;


public class MobileWalletPayment implements PaymentStrategy {
    private final String walletId;

    public MobileWalletPayment(String walletId) {
        this.walletId = walletId;
    }

    @Override
    public PaymentResult pay(Order order, double amount) {
        if (walletId != null && !walletId.isEmpty()) {
            order.setPaymentMethod("WALLET");
            return new PaymentResult(true, "Wallet payment completed", "WALLET");
        } else {
            return new PaymentResult(false, "Wallet error", null);
        }
    }
}
