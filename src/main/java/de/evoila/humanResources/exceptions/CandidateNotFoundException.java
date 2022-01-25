package de.evoila.humanResources.exceptions;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(Long id) {
        super("Candidate with id: " + id + " not found.");
    }

}
