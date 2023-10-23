package com.genesis.contactmanagement.application.company.dto;


import com.genesis.contactmanagement.domain.model.Company;
import jakarta.validation.constraints.NotBlank;

public record CompanyDto(
        @NotBlank(message = "Address is mandatory") String address,
        @NotBlank(message = "Vat number is mandatory") String vatNumber
) {

    public static CompanyDto from(Company company) {
        return new CompanyDto(
                company.getAddress(),
                company.getVatNumber()
        );
    }

    public Company toCompany() {
        Company company = new Company();
        company.setAddress(address);
        company.setVatNumber(vatNumber);
        return company;
    }
}
