package com.example.api.facade;
import org.springframework.stereotype.Service;
@Service
public class SmsService {
    public void send(String to, String msg) { System.out.println("[SMS] to=" + to + " msg=" + msg); }
}
