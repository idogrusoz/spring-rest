package com.genesis.contactmanagement.infrastructure.exceptionhandlers;

import com.genesis.contactmanagement.application.exceptions.ApplicationDataAccessException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ApplicationDataAccessExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ApplicationDataAccessException.class)
    public String duplicateVatNumberException() {
        return "Something went wrong, please try again";
    }

}
