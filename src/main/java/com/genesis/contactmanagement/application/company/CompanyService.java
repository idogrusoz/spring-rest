package com.genesis.contactmanagement.application.company;


import com.genesis.contactmanagement.application.company.dto.CompanyDto;
import com.genesis.contactmanagement.application.exceptions.ApplicationDataAccessException;
import com.genesis.contactmanagement.application.exceptions.CompanyNotFoundException;
import com.genesis.contactmanagement.application.exceptions.DuplicateVATException;
import com.genesis.contactmanagement.domain.model.Company;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

import static com.genesis.contactmanagement.utils.Utilities.isDuplicateVatNumber;
import static org.springframework.beans.BeanUtils.copyProperties;

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

    public CompanyDto findByVatNumber(String vatNumber) {
        try {
            Company company = companyRepository.findByVatNumber(vatNumber).orElseThrow();
            return CompanyDto.from(company);
        } catch (NoSuchElementException e) {
            log.error(e);
            throw new CompanyNotFoundException();
        }
    }

    @Transactional
    public CompanyDto updateCompany(CompanyDto companyDto) {
        try {
            var company = companyRepository.findByVatNumber(companyDto.vatNumber()).orElseThrow();
            copyProperties(companyDto, company);
            Company updatedCompany = companyRepository.saveAndFlush(company);
            return CompanyDto.from(updatedCompany);
        } catch (NoSuchElementException e) {
            log.error(e);
            throw new CompanyNotFoundException();
        } catch (DataAccessException e) {
            log.error(e);
            throw new ApplicationDataAccessException();
        }
    }


    @Transactional
    public List<CompanyDto> getAllCompanies() {
        return companyRepository.findAll().stream().map(CompanyDto::from).toList();
    }
}
