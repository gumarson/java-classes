package com.example.api.strategy;
import org.springframework.stereotype.Component;
@Component
public class CreditCardPayment implements PaymentStrategy {
    public double apply(double value) { return value * 1.03; }
    public String name() { return "credit"; }
}
