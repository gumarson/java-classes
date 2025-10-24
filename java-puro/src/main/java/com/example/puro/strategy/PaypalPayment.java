package com.example.puro.strategy;
public class PaypalPayment implements PaymentStrategy {
    public double apply(double value) { return value * 1.01; } // 1% taxa
    public String name() { return "paypal"; }
}
