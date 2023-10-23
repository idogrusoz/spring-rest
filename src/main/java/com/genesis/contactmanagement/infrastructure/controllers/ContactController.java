package com.genesis.contactmanagement.infrastructure.controllers;

import com.genesis.contactmanagement.application.contact.ContactService;
import com.genesis.contactmanagement.application.contact.dto.ContactDto;
import com.genesis.contactmanagement.application.contact.dto.CreateContactDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@Validated
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    ContactDto createContact(@Valid @RequestBody CreateContactDto contactDto) {
        return contactService.createContact(contactDto);
    }

    @PutMapping("/{contactId}")
    ContactDto updateContact(@PathVariable(name = "contactId") UUID contactId, @Valid @RequestBody CreateContactDto contactDto) {
        return contactService.updateContact(contactId, contactDto);
    }
}
