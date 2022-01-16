package de.evoila.personalAbteilung.exceptions;

public class BewerberNotFoundException extends RuntimeException {

    public BewerberNotFoundException(Long id) {
        super("Keine Bewerber mit id: " + id);
    }

}
