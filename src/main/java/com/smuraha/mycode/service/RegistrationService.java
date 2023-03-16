package com.smuraha.mycode.service;

public interface RegistrationService {
    boolean registerEmail(String email);
    boolean resetPasswordEmail(String email);
}
