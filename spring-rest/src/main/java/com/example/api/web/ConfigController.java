package com.example.api.web;

import com.example.api.config.AppConfigSingleton;
import org.springframework.web.bind.annotation.*;

@RestController
public class ConfigController {
    private final AppConfigSingleton cfg;
    public ConfigController(AppConfigSingleton cfg) { this.cfg = cfg; }

    @GetMapping("/config")
    public String info() {
        long hits = cfg.registerHit();
        return cfg.getAppName() + " | hits=" + hits;
    }
}
