package de.evoila.personalAbteilung;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.evoila.personalAbteilung.controllers.CandidateController;
import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.dtos.PositionDto;
import de.evoila.personalAbteilung.exceptions.CandidateNotFoundException;
import de.evoila.personalAbteilung.services.CandidateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandidateController.class)
public class CandidateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    CandidateService candidateService;

    private PositionDto p1Dto;
    private CandidateDto c1Dto;
    private CandidateDto c2Dto;
    private List<CandidateDto> candidateDtoList;

    @BeforeEach
    public void init() {
        p1Dto = new PositionDto("Backend", "AB", 5000L);
        c1Dto = new CandidateDto("Peter", "Parker", "pp@gmail.com", 3500L, p1Dto);
        c2Dto = new CandidateDto("Mary", "Jane", "mj@gmail.com", 4500L, p1Dto);
        candidateDtoList = List.of(c1Dto, c2Dto);
    }

    @Test
    public void getAllCandidatesShouldReturnOkAndListOfCandidates() throws Exception {
        Mockito.doReturn(candidateDtoList).when(candidateService).getAllCandidates();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(candidateDtoList)));
    }

    @Test
    public void findCandidateByIdShouldReturnOkAndTheCorrespondentCandidate() throws Exception {
        Mockito.doReturn(c1Dto).when(candidateService).findCandidateById(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(c1Dto)));
    }

    @Test
    public void findCandidateByIdShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        Mockito.when(candidateService.findCandidateById(5L)).thenThrow(new CandidateNotFoundException(5L));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void createCandidateShouldReturnOkAndTheCreatedCandidate() throws Exception {
        Mockito.doReturn(c2Dto).when(candidateService).createCandidate(c2Dto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/candidate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c2Dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(c2Dto)));
    }

    @Test
    public void createCandidateShouldReturnBadRequestWhenTheDataIsInvalid() throws Exception {
        Mockito.doReturn(c1Dto).when(candidateService).createCandidate(c1Dto);

        c1Dto.setDesiredSalary(null);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/candidate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c1Dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCandidateShouldReturnOk() throws Exception {
        Mockito.doNothing().when(candidateService).deleteCandidate(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/candidate/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCandidateShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        Mockito.doThrow(new CandidateNotFoundException(5L)).when(candidateService).deleteCandidate(5L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/candidate/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void updateCandidateShouldReturnOkAndTheUpdatedCandidate() throws Exception {
        Mockito.doReturn(c1Dto).when(candidateService).updateCandidate(c1Dto, 1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/candidate/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c1Dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(c1Dto)));
    }

    @Test
    public void updateCandidateShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        Mockito.when(candidateService.updateCandidate(c2Dto, 5L)).thenThrow(new CandidateNotFoundException(5L));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/candidate/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c2Dto)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void updateCandidateShouldReturnBadRequestWhenTheDataIsInvalid() throws Exception {
        Mockito.doReturn(c1Dto).when(candidateService).updateCandidate(c1Dto, 2L);

        c1Dto.setLastName(null);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/candidate/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(c1Dto)))
                .andExpect(status().isBadRequest());
    }

}
