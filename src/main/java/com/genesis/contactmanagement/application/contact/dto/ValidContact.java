package com.genesis.contactmanagement.application.contact.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ContactValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidContact {
    String message() default "A freelancer must have a VAT Number.";

    Class<? extends Payload>[] payload() default {};

    Class<?>[] groups() default {};
}
