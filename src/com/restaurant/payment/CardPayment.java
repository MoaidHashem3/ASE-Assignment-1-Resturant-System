package com.restaurant.payment;

import com.restaurant.domain.Order;


//card payment: succeed if card number length > 10

public class CardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public PaymentResult pay(Order order, double amount) {
        if (cardNumber != null && cardNumber.length() > 10) {
            order.setPaymentMethod("CARD");
            return new PaymentResult(true, "Card charged successfully", "CARD");
        } else {
            return new PaymentResult(false, "Card declined", null);
        }
    }
}
