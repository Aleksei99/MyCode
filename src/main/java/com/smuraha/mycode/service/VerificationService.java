package com.smuraha.mycode.service;

public interface VerificationService {
    boolean verified(String email,String token);
}
