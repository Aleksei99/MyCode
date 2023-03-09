package com.smuraha.mycode.customAnnotation;

import com.smuraha.mycode.dao.repo.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailValidator implements ConstraintValidator<UniqueEmail,String> {

    private UserRepository userRepository;
    @Autowired
    public EmailValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public EmailValidator() {
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            return userRepository.findUserByEmail(s) == null;
        } catch (NullPointerException e) {
            return true;
        }
    }
}
