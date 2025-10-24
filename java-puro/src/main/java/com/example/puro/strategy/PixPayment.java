package com.example.puro.strategy;
public class PixPayment implements PaymentStrategy {
    public double apply(double value) { return value * 0.98; } // 2% desconto
    public String name() { return "pix"; }
}
