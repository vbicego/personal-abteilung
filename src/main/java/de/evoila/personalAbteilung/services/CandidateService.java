package de.evoila.personalAbteilung.services;

import de.evoila.personalAbteilung.dtos.CandidateDto;

import java.util.List;

public interface CandidateService {

    public abstract List<CandidateDto> getAllCandidates();

    public abstract CandidateDto findCandidateById(Long id);

    public abstract CandidateDto createCandidate(CandidateDto candidateToCreate);

    public abstract void deleteCandidate(Long id);

    public abstract CandidateDto updateCandidate(CandidateDto candidateToUpdate, Long id);

}

