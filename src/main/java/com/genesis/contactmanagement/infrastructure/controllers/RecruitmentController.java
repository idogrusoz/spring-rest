package com.genesis.contactmanagement.infrastructure.controllers;

import com.genesis.contactmanagement.application.recruitment.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/recruitments")
@Validated
public class RecruitmentController {
    @Autowired
    RecruitmentService recruitmentService;

    @PostMapping("/companies/{companyVat}/contacts/{contactId}")
    void recruitContact(@PathVariable(name = "companyVat") String companyVat, @PathVariable(name = "contactId") UUID contactId) {
        recruitmentService.recruitContact(companyVat, contactId);
    }
}
