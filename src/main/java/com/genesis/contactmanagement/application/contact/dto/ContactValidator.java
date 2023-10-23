package com.genesis.contactmanagement.application.contact.dto;

import com.genesis.contactmanagement.domain.model.ContractType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContactValidator implements ConstraintValidator<ValidContact, CreateContactDto> {
    @Override
    public boolean isValid(CreateContactDto contact, ConstraintValidatorContext context) {
        if (ContractType.FREELANCER.equals(contact.contractType())) {
            return contact.vatNumber() != null && !contact.vatNumber().isBlank();
        }
        return true;
    }
}
