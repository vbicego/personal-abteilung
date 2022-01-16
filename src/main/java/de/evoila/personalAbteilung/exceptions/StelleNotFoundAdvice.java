package de.evoila.personalAbteilung.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class StelleNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(StelleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String stelleNotFoundHandler(StelleNotFoundException ex) {
        return ex.getMessage();
    }

}
