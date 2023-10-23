package com.genesis.contactmanagement.application.contact;

import com.genesis.contactmanagement.domain.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByContactId(UUID contactId);
}
