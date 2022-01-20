package de.evoila.humanResources.services;

import de.evoila.humanResources.dtos.CandidateDto;
import de.evoila.humanResources.exceptions.CandidateNotFoundException;
import de.evoila.humanResources.models.Candidate;
import de.evoila.humanResources.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidateList = candidateRepository.findAll();
        return candidateList.stream()
                .map(Candidate::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateDto findCandidateById(Long id) {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return foundCandidate.convertEntityToDto();
    }

    @Override
    public CandidateDto createCandidate(CandidateDto candidateToCreateDto) {
        Candidate candidateToCreate = candidateToCreateDto.convertDtoToEntity();
        Candidate createdCandidate = candidateRepository.save(candidateToCreate);
        return createdCandidate.convertEntityToDto();
    }

    @Override
    public void deleteCandidate(Long id) {
        candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        candidateRepository.deleteById(id);
    }

    @Override
    public CandidateDto updateCandidate(CandidateDto candidateToUpdate, Long id) {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));

        foundCandidate.setFirstName(candidateToUpdate.getFirstName());
        foundCandidate.setLastName(candidateToUpdate.getLastName());
        foundCandidate.setEmail(candidateToUpdate.getEmail());
        foundCandidate.setDesiredSalary(candidateToUpdate.getDesiredSalary());

        Candidate updatedCandidate = candidateRepository.save(foundCandidate);
        return updatedCandidate.convertEntityToDto();
    }
}
