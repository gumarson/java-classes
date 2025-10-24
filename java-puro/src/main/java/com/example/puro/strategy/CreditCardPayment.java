package com.example.puro.strategy;
public class CreditCardPayment implements PaymentStrategy {
    public double apply(double value) { return value * 1.03; } // 3% taxa
    public String name() { return "credit"; }
}
