package de.evoila.humanResources;

import de.evoila.humanResources.dtos.CandidateDto;
import de.evoila.humanResources.exceptions.CandidateNotFoundException;
import de.evoila.humanResources.models.Candidate;
import de.evoila.humanResources.repositories.CandidateRepository;
import de.evoila.humanResources.services.CandidateServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class CandidateServiceTest {

    @InjectMocks
    CandidateServiceImp candidateServiceImp;

    @Mock
    CandidateRepository candidateRepository;

    private CandidateDto c1Dto;
    private CandidateDto c2Dto;
    private List<CandidateDto> candidateDtoList;

    private Candidate c1;
    private Candidate c2;
    private List<Candidate> candidateList;

    @BeforeEach
    void setUp() {

        c1Dto = new CandidateDto();
        c1Dto.setFirstName("Peter");
        c1Dto.setLastName("Parker");
        c1Dto.setEmail("pp@gmail.com");
        c1Dto.setDesiredSalary(3500L);

        c2Dto = new CandidateDto();
        c2Dto.setFirstName("Mary");
        c2Dto.setLastName("Jane");
        c2Dto.setEmail("mj@gmail.com");
        c2Dto.setDesiredSalary(4500L);

        candidateDtoList = List.of(c1Dto, c2Dto);

        c1 = new Candidate();
        c1.setFirstName("Peter");
        c1.setLastName("Parker");
        c1.setEmail("pp@gmail.com");
        c1.setDesiredSalary(3500L);

        c2 = new Candidate();
        c2.setFirstName("Mary");
        c2.setLastName("Jane");
        c2.setEmail("mj@gmail.com");
        c2.setDesiredSalary(4500L);

        candidateList = List.of(c1, c2);
    }

    @Test
    public void getAllCandidatesIdShouldReturnCandidateDto() {
        Mockito.when(candidateRepository.findAll()).thenReturn(candidateList);

        assertEquals(candidateDtoList, candidateServiceImp.getAllCandidates());
    }

    @Test
    public void findCandidateByIdShouldReturnCandidateDto() {
        Mockito.when(candidateRepository.findById(1L)).thenReturn(Optional.ofNullable(c1));

        assertEquals(c1Dto, candidateServiceImp.findCandidateById(1L));
    }

    @Test
    public void findCandidateByIdShouldThrowAnExceptionWhenTheGivenIdNotCorrespondToAnyCandidate() {
        Mockito.when(candidateRepository.findById(5L)).thenThrow(new CandidateNotFoundException(5L));

        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateServiceImp.findCandidateById(5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

    @Test
    public void createCandidateShouldReturnCandidateDto() {
        Mockito.when(candidateRepository.save(any(Candidate.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());

        assertEquals(c1Dto, candidateServiceImp.createCandidate(c1Dto));
    }

    @Test
    public void deleteCandidateShouldThrowAnExceptionWhenTheGivenIdNotCorrespondToAnyCandidate() {
        Mockito.when(candidateRepository.findById(5L)).thenThrow(new CandidateNotFoundException(5L));

        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateServiceImp.deleteCandidate(5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

    @Test
    public void updateCandidateShouldReturnCandidateDto() {
        Mockito.when(candidateRepository.save(any(Candidate.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
        Mockito.when(candidateRepository.findById(2L)).thenReturn(Optional.ofNullable(c2));

        assertEquals(c2Dto, candidateServiceImp.updateCandidate(c2Dto, 2L));
    }

    @Test
    public void updateCandidateShouldRThrowAnExceptionWhenTheGivenIdNotCorrespondToAnyCandidate() {
        Mockito.when(candidateRepository.findById(5L)).thenThrow(new CandidateNotFoundException(5L));

        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateServiceImp.updateCandidate(c2Dto, 5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

}
