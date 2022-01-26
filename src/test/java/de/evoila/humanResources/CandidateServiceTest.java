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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class CandidateServiceTest {

    @InjectMocks
    private CandidateServiceImp candidateServiceImp;

    @Mock
    private CandidateRepository candidateRepository;

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
        Mockito.when(candidateRepository.findById(1L)).thenReturn(Optional.of(c1));

        assertEquals(c1Dto, candidateServiceImp.findCandidateById(1L));
    }

    @Test
    public void findCandidateByIdShouldThrowAnExceptionWhenTheGivenIdNotCorrespondToAnyCandidate() {
        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateServiceImp.findCandidateById(5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

    @Test
    public void createCandidateShouldReturnCandidateDto() {
        long expectedId = 5;

        Mockito.when(candidateRepository.save(any(Candidate.class))).thenAnswer((Answer<Candidate>) invocation -> {
            Candidate entity = invocation.getArgument(0, Candidate.class);
            entity.setId(expectedId);
            return entity;
        });

        CandidateDto returnedDto = candidateServiceImp.createCandidate(c1Dto);

        CandidateDto expectedDto = new CandidateDto();
        expectedDto.setId(5L);
        expectedDto.setFirstName("Peter");
        expectedDto.setLastName("Parker");
        expectedDto.setEmail("pp@gmail.com");
        expectedDto.setDesiredSalary(3500L);

        assertEquals(expectedDto, returnedDto);
    }

    @Test
    public void deleteCandidateShouldThrowAnExceptionWhenTheGivenIdNotCorrespondToAnyCandidate() {
        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateServiceImp.deleteCandidate(5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

    @Test
    public void updateCandidateShouldReturnCandidateDto() {
        long expectedId = 1;

        Mockito.when(candidateRepository.findById(1L)).thenReturn(Optional.of(c1));
        Mockito.when(candidateRepository.save(any(Candidate.class))).thenAnswer((Answer<Candidate>) invocation -> {
            Candidate entity = invocation.getArgument(0, Candidate.class);
            entity.setId(expectedId);
            return entity;
        });

        CandidateDto returnedDto = candidateServiceImp.updateCandidate(c1Dto, 1L);

        CandidateDto expectedDto = new CandidateDto();
        expectedDto.setId(1L);
        expectedDto.setFirstName("Peter");
        expectedDto.setLastName("Parker");
        expectedDto.setEmail("pp@gmail.com");
        expectedDto.setDesiredSalary(3500L);

        assertEquals(expectedDto, returnedDto);
    }

    @Test
    public void updateCandidateShouldRThrowAnExceptionWhenTheGivenIdNotCorrespondToAnyCandidate() {
        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateServiceImp.updateCandidate(c2Dto, 5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

}
