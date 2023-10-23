package com.genesis.contactmanagement.application.contact.dto;


import com.genesis.contactmanagement.application.company.dto.CompanyDto;
import com.genesis.contactmanagement.domain.model.Contact;
import com.genesis.contactmanagement.domain.model.ContractType;

import java.util.List;
import java.util.UUID;

public record ContactDto(
        UUID contactId,
        String firstName,
        String lastName,
        String address,
        String vatNumber,
        ContractType contractType,
        List<CompanyDto> companies) {

    public static ContactDto from(Contact contact) {
        return new ContactDto(
                contact.getContactId(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.getAddress(),
                contact.getVatNumber(),
                contact.getContractType(),
                contact.getCompanies().stream().map(CompanyDto::from).toList()
        );
    }
}
