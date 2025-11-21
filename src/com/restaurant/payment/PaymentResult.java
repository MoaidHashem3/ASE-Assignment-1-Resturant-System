package com.restaurant.payment;


public class PaymentResult {
    private final boolean success;
    private final String message;
    private final String method;

    public PaymentResult(boolean success, String message, String method) {
        this.success = success;
        this.message = message;
        this.method = method;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getMethod() { return method; }

    @Override
    public String toString() {
        return "PaymentResult {" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", method='" + method + '\'' +
                '}';
    }

}
