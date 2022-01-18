package de.evoila.personalAbteilung.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.exceptions.CandidateNotFoundException;
import de.evoila.personalAbteilung.models.Candidate;
import de.evoila.personalAbteilung.repositories.CandidateRepository;
import de.evoila.personalAbteilung.views.CandidateViews;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final ObjectMapper objectMapper;

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidateList = candidateRepository.findAll();
        return candidateList.stream()
                .map(Candidate::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String findCandidateByIdNormal(Long id) throws JsonProcessingException {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return objectMapper.writerWithView(CandidateViews.Normal.class).writeValueAsString(foundCandidate.convertEntityToDto());
    }

    @Override
    public String findCandidateByIdHr(Long id) throws JsonProcessingException {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return objectMapper.writerWithView(CandidateViews.Hr.class).writeValueAsString(foundCandidate.convertEntityToDto());
    }

    @Override
    public CandidateDto createCandidate(CandidateDto candidateToCreateDto) {
        Candidate candidateToCreate = candidateToCreateDto.convertDtoToEntity();
        Candidate createdCandidate;
        createdCandidate = candidateRepository.save(candidateToCreate);
        System.out.println(createdCandidate);
        System.out.println(candidateToCreate);
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
