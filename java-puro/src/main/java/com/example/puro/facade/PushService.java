package com.example.puro.facade;
public class PushService {
    public void send(String to, String msg) {
        System.out.println("[Push] to=" + to + " msg=" + msg);
    }
}
