package com.example.puro.facade;
public class SmsService {
    public void send(String to, String msg) {
        System.out.println("[SMS] to=" + to + " msg=" + msg);
    }
}
