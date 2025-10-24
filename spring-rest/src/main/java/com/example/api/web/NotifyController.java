package com.example.api.web;

import com.example.api.facade.NotificationFacade;
import org.springframework.web.bind.annotation.*;

record NotifyRequest(String to, String phone, String message) {}

@RestController
@RequestMapping("/notify")
public class NotifyController {
    private final NotificationFacade facade;
    public NotifyController(NotificationFacade facade) { this.facade = facade; }

    @PostMapping
    public String send(@RequestBody NotifyRequest req) {
        facade.notifyAllChannels(req.to(), req.phone(), req.message());
        return "OK";
    }
}
