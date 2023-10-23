package com.genesis.contactmanagement.infrastructure.exceptionhandlers;

import com.genesis.contactmanagement.application.exceptions.ContactNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class EntityNotFoundExceptionHandler {
    @ResponseStatus(NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ContactNotFoundException.class)
    public String contactNotFoundException() {
        return "No contact is found with the given credentials";
    }
}
