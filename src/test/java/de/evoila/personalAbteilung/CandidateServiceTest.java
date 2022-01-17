package de.evoila.personalAbteilung;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.models.Candidate;
import de.evoila.personalAbteilung.repositories.CandidateRepository;
import de.evoila.personalAbteilung.services.CandidateService;
import de.evoila.personalAbteilung.services.CandidateServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CandidateServiceTest {

    @InjectMocks
    CandidateServiceImp candidateServiceImp;

    @Mock
    CandidateRepository candidateRepository;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    ModelMapper modelMapper;

    private CandidateDto c1Dto;
    private CandidateDto c2Dto;
    private List<CandidateDto> candidateDtoList;

    private Candidate c1;
    private Candidate c2;
    private List<Candidate> candidateList;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

        c1Dto = new CandidateDto("Peter", "Parker", "pp@gmail.com", 3500L);
        c2Dto = new CandidateDto("Mary", "Jane", "mj@gmail.com", 4500L);
        candidateDtoList = List.of(c1Dto, c2Dto);

        c1 = new Candidate("Peter", "Parker", "pp@gmail.com", 3500L);
        c2 = new Candidate("Mary", "Jane", "mj@gmail.com", 4500L);
        candidateList = List.of(c1, c2);
    }

    @Test
    public void getAllCandidatesShouldReturnListOfCandidatesDtos() {
        Mockito.when(candidateRepository.findAll()).thenReturn(candidateList);
        Mockito.when(modelMapper.map(c1, CandidateDto.class)).thenReturn(c1Dto);
        Mockito.when(modelMapper.map(c2, CandidateDto.class)).thenReturn(c2Dto);

        assertEquals(candidateDtoList, candidateServiceImp.getAllCandidates());
    }

    @Test
    public void createCandidateShouldReturnCandidateDto() {
       Mockito.doReturn(c1).when(candidateRepository).save(c1);
       Mockito.when(modelMapper.map(c1, CandidateDto.class)).thenReturn(c1Dto);
       Mockito.when(modelMapper.map(c1Dto, Candidate.class)).thenReturn(c1);

        assertEquals(c1Dto, candidateServiceImp.createCandidate(c1Dto));
    }

}
