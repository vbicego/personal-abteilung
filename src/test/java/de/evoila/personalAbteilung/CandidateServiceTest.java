package de.evoila.personalAbteilung;

import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.models.Candidate;
import de.evoila.personalAbteilung.repositories.CandidateRepository;
import de.evoila.personalAbteilung.services.CandidateServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        c1Dto = new CandidateDto("Peter", "Parker", "pp@gmail.com", 3500L);
        c2Dto = new CandidateDto("Mary", "Jane", "mj@gmail.com", 4500L);
        candidateDtoList = List.of(c1Dto, c2Dto);

        c1 = new Candidate("Peter", "Parker", "pp@gmail.com", 3500L);
        c2 = new Candidate("Mary", "Jane", "mj@gmail.com", 4500L);
        candidateList = List.of(c1, c2);
    }

    @Test
    public void createCandidateShouldReturnCandidateDto() {
        Mockito.when(candidateRepository.save(c1)).thenReturn(c1);

        Candidate createdOne = candidateRepository.save(c1);

        System.out.println();
        System.out.println(createdOne.convertEntityToDto());
        System.out.println();

        assertEquals(c1Dto, candidateServiceImp.createCandidate(c1Dto));
    }

}
