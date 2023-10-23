package com.genesis.contactmanagement.infrastructure.controllers;

import com.genesis.contactmanagement.application.company.CompanyService;
import com.genesis.contactmanagement.application.company.dto.CompanyDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    CompanyDto createCompany(@Valid @RequestBody CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }
}
