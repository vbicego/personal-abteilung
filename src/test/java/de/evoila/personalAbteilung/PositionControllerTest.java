package de.evoila.personalAbteilung;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.evoila.personalAbteilung.controllers.PositionController;
import de.evoila.personalAbteilung.dtos.PositionDto;
import de.evoila.personalAbteilung.exceptions.PositionNotFoundException;
import de.evoila.personalAbteilung.services.PositionService;
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

@WebMvcTest(PositionController.class)
public class PositionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PositionService positionService;

    private PositionDto p1Dto;
    private PositionDto p2Dto;
    private List<PositionDto> positionDtoList;

    @BeforeEach
    public void init() {
        p1Dto = new PositionDto("Backend", "AB", 5000L);
        p2Dto = new PositionDto("Frontend", "CD", 4000L);
        positionDtoList = List.of(p1Dto, p2Dto);
    }

    @Test
    public void getAllPositionsShouldReturnFoundAndListOfPositions() throws Exception {
        Mockito.doReturn(positionDtoList).when(positionService).getAllPositions();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/position"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(positionDtoList)));
    }

    @Test
    public void findPositionByIdShouldReturnOkAndTheCorrespondentPosition() throws Exception {
        Mockito.doReturn(p1Dto).when(positionService).findPositionById(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/position/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1Dto)));
    }

    @Test
    public void findPositionShouldReturnNotFoundWhenTheIdNotCorrespondToAnyPosition() throws Exception {
        Mockito.when(positionService.findPositionById(5L)).thenThrow(new PositionNotFoundException(5L));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/position/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Position with id: 5 not found."));
    }

    @Test
    public void createPositionShouldReturnOkAndTheCreatedProject() throws Exception {
        Mockito.doReturn(p2Dto).when(positionService).createPosition(p2Dto);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/position")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p2Dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p2Dto)));
    }

    @Test
    public void createPositionShouldReturnBadRequestWhenTheDataIsInvalid() throws Exception {
        Mockito.doReturn(p2Dto).when(positionService).createPosition(p2Dto);

        p2Dto.setTitle(null);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/position")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p2Dto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deletePositionShouldReturnOk() throws Exception {
        Mockito.doNothing().when(positionService).deletePosition(1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/position/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePositionShouldReturnNotFoundWhenTheIdNotCorrespondToAnyPosition() throws Exception {
        Mockito.doThrow(new PositionNotFoundException(5L)).when(positionService).deletePosition(5L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/position/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Position with id: 5 not found."));
    }

    @Test
    public void updatePositionShouldReturnOkAndTheUpdatedPosition() throws Exception {
        Mockito.doReturn(p1Dto).when(positionService).updatePosition(p1Dto, 1L);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/position/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p1Dto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(p1Dto)));
    }

    @Test
    public void updatePositionShouldReturnNotFoundWhenTheIdNotCorrespondToAnyPosition() throws Exception {
        Mockito.when(positionService.updatePosition(p1Dto, 5L)).thenThrow(new PositionNotFoundException(5L));

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/position/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p1Dto)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Position with id: 5 not found."));
    }

    @Test
    public void updatePositionShouldReturnBadRequestWhenTheDataIsInvalid() throws Exception {
        Mockito.doReturn(p2Dto).when(positionService).updatePosition(p2Dto, 2L);

        p2Dto.setDescription(null);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/position/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p2Dto)))
                .andExpect(status().isBadRequest());
    }

}
