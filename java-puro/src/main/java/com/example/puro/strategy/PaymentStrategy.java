package com.example.puro.strategy;
public interface PaymentStrategy {
    double apply(double value);
    String name();
}
