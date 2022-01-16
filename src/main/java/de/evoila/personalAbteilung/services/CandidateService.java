package de.evoila.personalAbteilung.services;

import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.dtos.PositionDto;
import de.evoila.personalAbteilung.exceptions.CandidateNotFoundException;
import de.evoila.personalAbteilung.models.Candidate;
import de.evoila.personalAbteilung.models.Position;
import de.evoila.personalAbteilung.repositories.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    public List<CandidateDto> getAllCandidates() {
        List<Candidate> candidateListList = candidateRepository.findAll();
        return candidateListList.stream()
                .map(candidate -> modelMapper.map(candidate, CandidateDto.class))
                .collect(Collectors.toList());
    }

    public CandidateDto findCandidateById(Long id) {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        return modelMapper.map(foundCandidate, CandidateDto.class);
    }

    public CandidateDto createCandidate(CandidateDto candidateToCreate) {
        Candidate createdCandidate = candidateRepository.save(modelMapper.map(candidateToCreate, Candidate.class));
        return modelMapper.map(createdCandidate, CandidateDto.class);
    }

    public void deleteCandidate(Long id) {
        candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));
        candidateRepository.deleteById(id);
    }

    public CandidateDto updateCandidate(CandidateDto candidateToUpdate, Long id) {
        Candidate foundCandidate = candidateRepository.findById(id).orElseThrow(() -> new CandidateNotFoundException(id));

        foundCandidate.setFirstName(candidateToUpdate.getFirstName());
        foundCandidate.setLastName(candidateToUpdate.getLastName());
        foundCandidate.setEmail(candidateToUpdate.getEmail());
        foundCandidate.setDesiredSalary(candidateToUpdate.getDesiredSalary());
        foundCandidate.setPosition(modelMapper.map(candidateToUpdate.getPositionDto(), Position.class));

        Candidate updatedCandidate = candidateRepository.save(foundCandidate);
        return modelMapper.map(updatedCandidate, CandidateDto.class);
    }

    public List<Candidate> convertListOfCandidateDtoIntoCandidate (List<CandidateDto> candidateDtoList){
        return candidateDtoList.stream()
                .map(candidateDto -> modelMapper.map(candidateDto, Candidate.class))
                .collect(Collectors.toList());
    }

}

