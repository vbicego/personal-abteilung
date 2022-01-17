package de.evoila.personalAbteilung.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.evoila.personalAbteilung.dtos.CandidateDto;

import java.util.List;

public interface CandidateService {

    public abstract List<CandidateDto> getAllCandidates();

    public abstract String findCandidateByIdNormal(Long id) throws JsonProcessingException;

    public abstract String findCandidateByIdHr(Long id) throws JsonProcessingException;

    public abstract CandidateDto createCandidate(CandidateDto candidateToCreate);

    public abstract void deleteCandidate(Long id);

    public abstract CandidateDto updateCandidate(CandidateDto candidateToUpdate, Long id);

}

