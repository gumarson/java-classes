package com.example.api.web;

import com.example.api.strategy.PaymentStrategyFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/price")
public class PricingController {
    private final PaymentStrategyFactory factory;
    public PricingController(PaymentStrategyFactory factory) { this.factory = factory; }

    @GetMapping("/checkout")
    public double checkout(@RequestParam double value, @RequestParam String strategy) {
        return factory.get(strategy).apply(value);
    }
}
