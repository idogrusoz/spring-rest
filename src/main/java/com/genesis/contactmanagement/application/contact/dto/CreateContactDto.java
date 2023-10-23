package com.genesis.contactmanagement.application.contact.dto;

import com.genesis.contactmanagement.domain.model.Contact;
import com.genesis.contactmanagement.domain.model.ContractType;
import jakarta.validation.constraints.NotBlank;

@ValidContact
public record CreateContactDto(
        @NotBlank(message = "First name is mandatory") String firstName,
        @NotBlank(message = "Last name is mandatory") String lastName,
        @NotBlank(message = "Address is mandatory") String address,
        String vatNumber,
        ContractType contractType) {

    public Contact toContact() {
        Contact contact = new Contact();
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setAddress(address);
        contact.setVatNumber(vatNumber);
        contact.setContractType(contractType);
        return contact;
    }
}
