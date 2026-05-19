package com.cauanengdev.pamydex.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AdultValidator.class)
public @interface ValidAdult {
    String message() default "Data Selecionada Menor que 18 Anos!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
