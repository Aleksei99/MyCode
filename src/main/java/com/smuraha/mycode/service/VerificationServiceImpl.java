package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.Verification;
import com.smuraha.mycode.dao.repo.VerificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final VerificationRepository verificationRepository;

    @Override
    public boolean verified(String email, String token) {
        final Verification verification = verificationRepository.findByTokenAndUsedIsFalseAndValidToAfterAndEmail(token, LocalDateTime.now(), email);
        boolean verified = verification != null;
        if(verified){
            verification.setUsed(true);
        }
        return verified;
    }
}
