package com.example.api.facade;
import org.springframework.stereotype.Component;
@Component
public class NotificationFacade {
    private final EmailService email; private final SmsService sms; private final PushService push;
    public NotificationFacade(EmailService email, SmsService sms, PushService push) {
        this.email = email; this.sms = sms; this.push = push;
    }
    public void notifyAllChannels(String emailTo, String phone, String message) {
        email.send(emailTo, message); sms.send(phone, message); push.send(emailTo, message);
    }
}
