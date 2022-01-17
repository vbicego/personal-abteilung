package de.evoila.personalAbteilung.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.evoila.personalAbteilung.views.CandidateViews;
import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.exceptions.CandidateNotFoundException;
import de.evoila.personalAbteilung.models.Candidate;
import de.evoila.personalAbteilung.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateServiceImp implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Override
    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidateListList = candidateRepository.findAll();
        return candidateListList.stream()
                .map(candidate -> modelMapper.map(candidate, CandidateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String findCandidateByIdNormal(Long id) throws JsonProcessingException {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return objectMapper.writerWithView(CandidateViews.Normal.class).writeValueAsString(modelMapper.map(foundCandidate, CandidateDto.class));
    }

    @Override
    public String findCandidateByIdHr(Long id) throws JsonProcessingException {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return objectMapper.writerWithView(CandidateViews.Hr.class).writeValueAsString(modelMapper.map(foundCandidate, CandidateDto.class));
    }

    @Override
    public CandidateDto createCandidate(CandidateDto candidateToCreate) {
        Candidate createdCandidate = candidateRepository.save(modelMapper.map(candidateToCreate, Candidate.class));
        return modelMapper.map(createdCandidate, CandidateDto.class);
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
        return modelMapper.map(updatedCandidate, CandidateDto.class);
    }
}
