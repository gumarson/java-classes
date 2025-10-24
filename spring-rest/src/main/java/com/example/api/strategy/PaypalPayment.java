package com.example.api.strategy;
import org.springframework.stereotype.Component;
@Component
public class PaypalPayment implements PaymentStrategy {
    public double apply(double value) { return value * 1.01; }
    public String name() { return "paypal"; }
}
