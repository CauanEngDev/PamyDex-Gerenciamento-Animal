package com.cauanengdev.pamydex.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AdultValidator implements ConstraintValidator<ValidAdult, LocalDate> {
    @Override
    public boolean isValid(LocalDate age, ConstraintValidatorContext context) {
        if (age == null) return true;
        return Period.between(age, LocalDate.now()).getYears() >= 18;
    }
}
