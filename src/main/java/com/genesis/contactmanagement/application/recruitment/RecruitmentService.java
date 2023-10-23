package com.genesis.contactmanagement.application.recruitment;

import com.genesis.contactmanagement.application.company.CompanyRepository;
import com.genesis.contactmanagement.application.contact.ContactRepository;
import com.genesis.contactmanagement.application.exceptions.ApplicationDataAccessException;
import com.genesis.contactmanagement.application.exceptions.CompanyNotFoundException;
import com.genesis.contactmanagement.application.exceptions.ContactNotFoundException;
import com.genesis.contactmanagement.domain.model.Company;
import com.genesis.contactmanagement.domain.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class RecruitmentService {
    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    ContactRepository contactRepository;

    @Transactional
    public void recruitContact(String companyVat, UUID contactId) {
        Company company = findCompany(companyVat);
        Contact contact = findContact(contactId);
        company.recruit(contact);
        try {
            contactRepository.saveAndFlush(contact);
        } catch (DataAccessException e) {
            throw new ApplicationDataAccessException();
        }
    }

    private Contact findContact(UUID contactId) {
        try {
            return contactRepository.findByContactId(contactId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new ContactNotFoundException();
        }
    }

    private Company findCompany(String companyVat) {
        try {
            return companyRepository.findByVatNumber(companyVat).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CompanyNotFoundException();
        }
    }
}
