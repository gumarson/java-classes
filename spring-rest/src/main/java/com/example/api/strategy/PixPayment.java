package com.example.api.strategy;
import org.springframework.stereotype.Component;
@Component
public class PixPayment implements PaymentStrategy {
    public double apply(double value) { return value * 0.98; }
    public String name() { return "pix"; }
}
