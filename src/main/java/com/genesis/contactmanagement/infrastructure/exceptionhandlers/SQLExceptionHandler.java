package com.genesis.contactmanagement.infrastructure.exceptionhandlers;

import com.genesis.contactmanagement.application.exceptions.DuplicateVATException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class SQLExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicateVATException.class)
    public String duplicateVatNumberException() {
        return "Entity with the same VAT Number already exists.";
    }

}
