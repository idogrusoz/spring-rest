package com.genesis.contactmanagement.application.company;

import com.genesis.contactmanagement.domain.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByVatNumber(String vatNumber);
}
