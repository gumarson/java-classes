package com.example.puro.strategy;
public class Checkout {
    private double value;
    private PaymentStrategy strategy;
    public Checkout(double value) { this.value = value; }
    public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
    public double total() {
        if (strategy == null) throw new IllegalStateException("Defina uma estratégia de pagamento");
        return strategy.apply(value);
    }
}
