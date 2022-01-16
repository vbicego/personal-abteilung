package de.evoila.personalAbteilung.exceptions;

public class StelleNotFoundException extends RuntimeException {

    public StelleNotFoundException(Long id) {
        super("Keine Stelle mit id: " + id);
    }
}
