package com.example.api.strategy;
public interface PaymentStrategy {
    double apply(double value);
    String name();
}
