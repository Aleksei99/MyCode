package com.smuraha.mycode.service;

import com.smuraha.mycode.dao.model.Verification;
import com.smuraha.mycode.dao.repo.VerificationRepository;
import com.smuraha.mycode.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final EmailService emailService;
    private final VerificationRepository verificationRepository;

    @Override
    @Transactional
    public boolean registerEmail(String email) {
        SecureRandom random = new SecureRandom();
        final int token = random.nextInt(99999999);
        verificationRepository.save(new Verification(
                token + "",
                email,
                "REGISTER",
                LocalDateTime.now().plusMinutes(15),
                false
        ));
        String message =
                """
                        Welcome to MyCode!
                                          
                        Thank you for registering and becoming a part of our community.
                                          
                        Your verification code is
                        """ + token + """            
                                        
                                        
                        Best regards, MyCode team.                  
                        """;
        return emailService.sendEmail(email, "Registration in MyCode", message);
    }

    @Override
    public boolean resetPasswordEmail(String email) {
        SecureRandom random = new SecureRandom();
        final int token = random.nextInt(99999999);
        verificationRepository.save(new Verification(
                token + "",
                email,
                "RESET",
                LocalDateTime.now().plusMinutes(15),
                false
        ));
        String message =
                """
                        You are gonna to reset your password!
                        if it wasn't you, please don't tell this code anybody
                                          
                        Your verification code to reset is
                        """ + token + """            
                                        
                                        
                        Best regards, MyCode team.                  
                        """;
        return emailService.sendEmail(email, "Reset password in MyCode", message);
    }


}
