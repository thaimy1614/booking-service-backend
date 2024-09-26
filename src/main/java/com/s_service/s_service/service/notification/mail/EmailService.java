package com.s_service.s_service.service.notification.mail;

public interface EmailService {
    void sendVerification(String name, String to, String url);

    void sendOtp(String topic, String email, String otp);

    void sendNewPassword(String topic, String email, String password);
}
