package com.seamfix.nimc.maybeach.controllers;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class CbsControllerAdvice {

    @ExceptionHandler(InvalidFormatException.class)
    public void handleInvalidFormatException(HttpServletResponse response, InvalidFormatException exception) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), exception.getOriginalMessage());
    }

}
