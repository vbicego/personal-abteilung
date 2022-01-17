package de.evoila.personalAbteilung;

import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.dtos.PositionDto;
import de.evoila.personalAbteilung.models.Candidate;
import de.evoila.personalAbteilung.models.Position;
import de.evoila.personalAbteilung.repositories.CandidateRepository;
import de.evoila.personalAbteilung.services.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    CandidateService candidateService;

    @MockBean
    CandidateRepository candidateRepository;

    private PositionDto p1Dto;
    private Position p1;

    private CandidateDto c1Dto;
    private CandidateDto c2Dto;
    private List<CandidateDto> candidateDtoList;

    private Candidate c1;
    private Candidate c2;
    private List<Candidate> candidateList;

    @BeforeEach
    public void init() {
        p1Dto = new PositionDto("Backend", "AB", 5000L);
        p1 = new Position("Backend", "AB", 5000L);

        c1Dto = new CandidateDto("Peter", "Parker", "pp@gmail.com", 3500L, p1Dto);
        c2Dto = new CandidateDto("Mary", "Jane", "mj@gmail.com", 4500L, p1Dto);
        candidateDtoList = List.of(c1Dto, c2Dto);

        c1 = new Candidate("Peter", "Parker", "pp@gmail.com", 3500L, p1);
        c2 = new Candidate("Mary", "Jane", "mj@gmail.com", 4500L, p1);
        candidateList = List.of(c1, c2);
    }

}
