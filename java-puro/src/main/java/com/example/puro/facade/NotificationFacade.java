package com.example.puro.facade;
public class NotificationFacade {
    private final EmailService email = new EmailService();
    private final SmsService sms = new SmsService();
    private final PushService push = new PushService();

    public void notifyAllChannels(String emailTo, String phone, String message) {
        email.send(emailTo, message);
        sms.send(phone, message);
        push.send(emailTo, message);
    }
}
