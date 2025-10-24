package com.example.puro.facade;
public class EmailService {
    public void send(String to, String msg) {
        System.out.println("[Email] to=" + to + " msg=" + msg);
    }
}
