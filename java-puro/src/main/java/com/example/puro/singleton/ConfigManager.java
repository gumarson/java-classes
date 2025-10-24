package com.example.puro.singleton;

import java.util.concurrent.ConcurrentHashMap;

public class ConfigManager {
    private static final ConfigManager INSTANCE = new ConfigManager();
    private final ConcurrentHashMap<String, String> store = new ConcurrentHashMap<>();

    private ConfigManager() {}

    public static ConfigManager getInstance() { return INSTANCE; }

    public void set(String key, String value) { store.put(key, value); }
    public String get(String key) { return store.get(key); }
}
