package com.cauanengdev.pamydex.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return true;
        try {
            String domain = email.split("@")[1];
            InitialDirContext ctx = new InitialDirContext();
            Attributes attrs = ctx.getAttributes(
                    "dns:/" + domain, new String[]{"MX"}
            );
            return attrs.get("MX") != null;
        } catch (Exception e) {
            return false;
        }
    }
}
