package com.example.puro;

import com.example.puro.singleton.ConfigManager;
import com.example.puro.strategy.*;
import com.example.puro.facade.NotificationFacade;

public class App {
    public static void main(String[] args) {
        System.out.println("=== JAVA PURO - PADRÕES ===");

        // Singleton
        ConfigManager cfg = ConfigManager.getInstance();
        cfg.set("app.name", "POC Padrões");
        System.out.println("Singleton app.name = " + cfg.get("app.name"));

        // Strategy
        Checkout checkout = new Checkout(100.0);
        checkout.setStrategy(new PixPayment());
        System.out.println("Strategy PIX total: " + checkout.total());
        checkout.setStrategy(new CreditCardPayment());
        System.out.println("Strategy CREDIT total: " + checkout.total());

        // Facade
        NotificationFacade facade = new NotificationFacade();
        facade.notifyAllChannels("user@example.com", "+5511999999999", "Hello from Facade!");
        System.out.println("Facade notificações enviadas.");
    }
}
