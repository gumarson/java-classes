package com.example.api.facade;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
    public void send(String to, String msg) { System.out.println("[Email] to=" + to + " msg=" + msg); }
}
