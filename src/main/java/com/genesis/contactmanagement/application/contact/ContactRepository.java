package com.genesis.contactmanagement.application.contact;

import com.genesis.contactmanagement.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
