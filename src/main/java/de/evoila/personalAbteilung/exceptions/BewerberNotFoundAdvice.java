package de.evoila.personalAbteilung.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BewerberNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(BewerberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bewerberNotFoundHandler(BewerberNotFoundException ex) {
        return ex.getMessage();
    }

}
