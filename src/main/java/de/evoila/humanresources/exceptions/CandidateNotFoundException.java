package de.evoila.humanresources.exceptions;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(Long id) {
        super("Candidate with id: " + id + " not found.");
    }

}
