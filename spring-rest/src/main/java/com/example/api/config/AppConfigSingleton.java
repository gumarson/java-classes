package com.example.api.config;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

/** Bean singleton (padrão do Spring). */
@Component
public class AppConfigSingleton {
    private final String appName = "API Padrões";
    private final AtomicLong hits = new AtomicLong();
    public String getAppName() { return appName; }
    public long registerHit() { return hits.incrementAndGet(); }
    public long getHits() { return hits.get(); }
}
