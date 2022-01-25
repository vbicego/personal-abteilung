package de.evoila.humanResources.services;

import de.evoila.humanResources.dtos.CandidateDto;

import java.util.List;

public interface CandidateService {

    public abstract List<CandidateDto> getAllCandidates();

    public abstract CandidateDto findCandidateById(Long id);

    public abstract CandidateDto createCandidate(CandidateDto candidateToCreateDto);

    public abstract void deleteCandidate(Long id);

    public abstract CandidateDto updateCandidate(CandidateDto candidateToUpdateDto, Long id);

}
