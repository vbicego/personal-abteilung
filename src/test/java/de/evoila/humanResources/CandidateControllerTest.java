package de.evoila.humanResources;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.evoila.humanResources.controllers.CandidateController;
import de.evoila.humanResources.dtos.CandidateDto;
import de.evoila.humanResources.exceptions.CandidateNotFoundException;
import de.evoila.humanResources.services.CandidateService;
import de.evoila.humanResources.views.CandidateViews;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CandidateController.class)
public class CandidateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CandidateService candidateService;

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
    public void getAllCandidatesShouldReturnOkAndListOfCandidates() throws Exception {
        Mockito.doReturn(candidateDtoList).when(candidateService).getAllCandidates();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(candidateDtoList)));
    }

    @Test
    public void findCandidateByIdNormalShouldReturnOkAndTheCorrespondentCandidate() throws Exception {
        Mockito.doReturn(c1Dto).when(candidateService).findCandidateById(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writerWithView(CandidateViews.Normal.class).writeValueAsString(c1Dto)));
    }

    @Test
    public void findCandidateByIdNormalShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        Mockito.when(candidateService.findCandidateById(5L)).thenThrow(new CandidateNotFoundException(5L));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void findCandidateByIdHrShouldReturnOkAndTheCorrespondentCandidate() throws Exception {
        Mockito.doReturn(c1Dto).when(candidateService).findCandidateById(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/1/hr-view"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writerWithView(CandidateViews.Hr.class).writeValueAsString(c1Dto)));
    }

    @Test
    public void findCandidateByIdHrShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        Mockito.when(candidateService.findCandidateById(5L)).thenThrow(new CandidateNotFoundException(5L));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/5/hr-view"))
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
