package com.genesis.contactmanagement.application.contact;

import com.genesis.contactmanagement.application.contact.dto.ContactDto;
import com.genesis.contactmanagement.application.contact.dto.CreateContactDto;
import com.genesis.contactmanagement.application.exceptions.ApplicationDataAccessException;
import com.genesis.contactmanagement.application.exceptions.DuplicateVATException;
import com.genesis.contactmanagement.domain.model.Contact;
import lombok.extern.jbosslog.JBossLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.genesis.contactmanagement.utils.Utilities.isDuplicateVatNumber;

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
}
