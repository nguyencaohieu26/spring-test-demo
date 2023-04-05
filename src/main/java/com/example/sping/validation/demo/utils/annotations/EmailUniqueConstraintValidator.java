package com.example.sping.validation.demo.utils.annotations;

import com.example.sping.validation.demo.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUnique,String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(EmailUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.findUserByEmail(s)
                .isEmpty();
    }
}
