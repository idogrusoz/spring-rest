package com.genesis.contactmanagement.application.contact;

import com.genesis.contactmanagement.application.contact.dto.ContactDto;
import com.genesis.contactmanagement.application.contact.dto.CreateContactDto;
import com.genesis.contactmanagement.application.exceptions.ApplicationDataAccessException;
import com.genesis.contactmanagement.application.exceptions.ContactNotFoundException;
import com.genesis.contactmanagement.application.exceptions.DuplicateVATException;
import com.genesis.contactmanagement.domain.model.Contact;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static com.genesis.contactmanagement.utils.Utilities.isDuplicateVatNumber;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
@JBossLog
public class ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Transactional
    public ContactDto createContact(CreateContactDto contactDto) {
        try {
            Contact contact = contactRepository.saveAndFlush(contactDto.toContact());
            return ContactDto.from(contact);
        }  catch (DataAccessException e) {
            if (isDuplicateVatNumber(e.getCause().getMessage())) {
                log.error(e);
                throw new DuplicateVATException();
            } else {
                log.error(e);
                throw new ApplicationDataAccessException();
            }
        }
    }

    @Transactional
    public ContactDto updateContact(UUID contactId, CreateContactDto contactDto) {
        try {
            Contact contact = contactRepository.findByContactId(contactId).orElseThrow();
            copyProperties(contactDto, contact);
            Contact updatedContact = contactRepository.saveAndFlush(contact);
            return ContactDto.from(updatedContact);
        } catch (DataAccessException e) {
            log.error(e);
            throw new ApplicationDataAccessException();
        } catch (NoSuchElementException e) {
            log.error(e);
            throw new ContactNotFoundException();
        }
    }

    @Transactional
    public void deleteContact(UUID contactId) {
        try {
            Contact contact = contactRepository.findByContactId(contactId).orElseThrow();
            contactRepository.delete(contact);
        } catch (NoSuchElementException e) {
            log.error(e);
            throw new ContactNotFoundException();
        } catch (IllegalArgumentException | OptimisticLockingFailureException e) {
            log.error(e);
            throw new ApplicationDataAccessException();
        }
    }

    public List<ContactDto> getAllContacts() {
        return contactRepository.findAll().stream().map(ContactDto::from).toList();
    }
}
