package de.evoila.personalAbteilung;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.evoila.personalAbteilung.dtos.PositionDto;
import de.evoila.personalAbteilung.models.Position;
import de.evoila.personalAbteilung.repositories.PositionRepository;
import de.evoila.personalAbteilung.services.PositionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PositionServiceTest {

    @MockBean
    PositionRepository positionRepository;

    private PositionService positionService;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        ObjectMapper objectMapper = new ObjectMapper();
        positionService = new PositionService(positionRepository, modelMapper, objectMapper);
    }

    private PositionDto p1Dto;
    private PositionDto p2Dto;
    private List<PositionDto> positionDtoList;

    private Position p1;
    private Position p2;
    private List<Position> positionList;

    @BeforeEach
    public void init() {
        p1Dto = new PositionDto("Backend", "AB", 5000L);
        p2Dto = new PositionDto("Frontend", "AB", 4000L);
        positionDtoList = List.of(p1Dto, p2Dto);

        p1 = new Position("Backend", "AB", 5000L);
        p2 = new Position("Frontend", "AB", 4000L);
        positionList = List.of(p1, p2);
    }

    @Test
    public void getAllPositionsShouldReturnListOfPositionsDto() throws Exception {
        Mockito.doReturn(positionList).when(positionRepository).findAll();

        assertEquals(positionDtoList, positionService.getAllPositions());
    }

    @Test
    public void findPositionByIdShouldReturnTheCorrespondentPositionDto() throws Exception {
        Mockito.when(positionRepository.findById(2L)).thenReturn(Optional.ofNullable(p2));

        assertEquals(p2Dto, positionService.findPositionById(2L));
    }

}
