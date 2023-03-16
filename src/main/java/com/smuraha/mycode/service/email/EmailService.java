package com.smuraha.mycode.service.email;

public interface EmailService {
    boolean sendEmail(String toEmail,String subject,String message);
}
