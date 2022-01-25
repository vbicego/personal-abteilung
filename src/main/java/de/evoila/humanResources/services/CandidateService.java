package de.evoila.humanResources.services;

import de.evoila.humanResources.dtos.CandidateDto;

import java.util.List;

public interface CandidateService {

    public List<CandidateDto> getAllCandidates();

    public CandidateDto findCandidateById(Long id);

    public CandidateDto createCandidate(CandidateDto candidateToCreateDto);

    public void deleteCandidate(Long id);

    public CandidateDto updateCandidate(CandidateDto candidateToUpdateDto, Long id);

}
