package com.example.api.facade;
import org.springframework.stereotype.Service;
@Service
public class PushService {
    public void send(String to, String msg) { System.out.println("[Push] to=" + to + " msg=" + msg); }
}
