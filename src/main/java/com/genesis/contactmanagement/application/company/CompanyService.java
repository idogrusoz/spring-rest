package com.genesis.contactmanagement.application.company;


import com.genesis.contactmanagement.application.company.dto.CompanyDto;
import com.genesis.contactmanagement.application.exceptions.ApplicationDataAccessException;
import com.genesis.contactmanagement.application.exceptions.DuplicateVATException;
import com.genesis.contactmanagement.domain.model.Company;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@JBossLog
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Transactional
    public CompanyDto createCompany(CompanyDto companyDto) {
        try {
            Company company = companyRepository.saveAndFlush(companyDto.toCompany());
            return CompanyDto.from(company);
        } catch (DataAccessException e) {
            if (isDuplicateVatNumber(e.getCause().getMessage())) {
                log.error(e);
                throw new DuplicateVATException();
            } else {
                log.error(e);
                throw new ApplicationDataAccessException();
            }
        }
    }

    private static boolean isDuplicateVatNumber(String message) {
        return message.contains("Unique index or primary key violation")
                && message.contains("VAT_NUMBER");
    }
}
