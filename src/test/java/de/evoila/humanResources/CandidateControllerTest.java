package de.evoila.humanResources;

import de.evoila.humanResources.controllers.CandidateController;
import de.evoila.humanResources.dtos.CandidateDto;
import de.evoila.humanResources.exceptions.CandidateNotFoundException;
import de.evoila.humanResources.services.CandidateServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CandidateControllerTest {

    @Mock
    private CandidateServiceImp candidateServiceImp;

    @InjectMocks
    private CandidateController candidateController;

    private CandidateDto c1Dto;
    private CandidateDto c2Dto;
    private List<CandidateDto> candidateDtoList;

    @BeforeEach
    public void init() {
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
    }

    @Test
    public void getAllCandidatesShouldReturnOkAndListOfCandidates() {
        Mockito.doReturn(candidateDtoList).when(candidateServiceImp).getAllCandidates();

        assertEquals(candidateDtoList, candidateController.getAllCandidates());
    }

    @Test
    public void findCandidateByIdNormalShouldReturnOkAndTheCorrespondentCandidate() {
        Mockito.doReturn(c1Dto).when(candidateServiceImp).findCandidateById(1L);

        assertEquals(c1Dto, candidateController.findCandidateById(1L));
    }

    @Test
    public void findCandidateByIdNormalShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() {
        Mockito.when(candidateServiceImp.findCandidateById(5L)).thenThrow(new CandidateNotFoundException(5L));

        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateController.findCandidateById(5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

    @Test
    public void findCandidateByIdHrShouldReturnOkAndTheCorrespondentCandidate() {
        Mockito.doReturn(c1Dto).when(candidateServiceImp).findCandidateById(1L);

        assertEquals(c1Dto, candidateController.findCandidateByIdHr(1L));
    }

    @Test
    public void findCandidateByIdHrShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() {
        Mockito.when(candidateServiceImp.findCandidateById(5L)).thenThrow(new CandidateNotFoundException(5L));

        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateController.findCandidateByIdHr(5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

    @Test
    public void createCandidateShouldReturnOkAndTheCreatedCandidate() {
        Mockito.doReturn(c2Dto).when(candidateServiceImp).createCandidate(c2Dto);

        assertEquals(c2Dto, candidateController.createCandidate(c2Dto));
    }

    @Test
    public void deleteCandidateShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() {
        Mockito.doThrow(new CandidateNotFoundException(5L)).when(candidateServiceImp).deleteCandidate(5L);

        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateController.deleteCandidate(5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

    @Test
    public void updateCandidateShouldReturnOkAndTheUpdatedCandidate() {
        Mockito.doReturn(c1Dto).when(candidateServiceImp).updateCandidate(c1Dto, 1L);

        assertEquals(c1Dto, candidateController.updateCandidate(c1Dto, 1L));
    }

    @Test
    public void updateCandidateShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() {
        Mockito.when(candidateServiceImp.updateCandidate(c2Dto, 5L)).thenThrow(new CandidateNotFoundException(5L));

        CandidateNotFoundException exp = Assertions.assertThrows(CandidateNotFoundException.class, () -> candidateController.updateCandidate(c2Dto, 5L));

        assertEquals("Candidate with id: 5 not found.", exp.getMessage());
    }

}
