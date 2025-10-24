package com.example.api.strategy;

import java.util.Map;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class PaymentStrategyFactory {
    private final Map<String, PaymentStrategy> byName;
    public PaymentStrategyFactory(java.util.List<PaymentStrategy> strategies) {
        this.byName = strategies.stream().collect(Collectors.toMap(PaymentStrategy::name, s -> s));
    }
    public PaymentStrategy get(String name) {
        PaymentStrategy s = byName.get(name);
        if (s == null) throw new IllegalArgumentException("Estratégia inválida: " + name);
        return s;
    }
}
