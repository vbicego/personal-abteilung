package de.evoila.personalAbteilung.exceptions;

public class PositionNotFoundException extends RuntimeException {

    public PositionNotFoundException(Long id) {
        super("Position with id: " + id + " not found.");
    }
}
