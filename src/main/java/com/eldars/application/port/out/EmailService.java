package com.eldars.application.port.out;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}