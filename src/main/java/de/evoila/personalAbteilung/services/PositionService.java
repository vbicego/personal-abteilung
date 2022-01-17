package de.evoila.personalAbteilung.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.evoila.personalAbteilung.dtos.PositionDto;

import java.util.List;

public interface PositionService {

    public abstract List<PositionDto> getAllPositions();

    public abstract String findPositionById(Long id) throws JsonProcessingException;

    public abstract String findPositionByIdHr(Long id) throws JsonProcessingException;

    public abstract PositionDto createPosition(PositionDto positionToCreate);

    public abstract void deletePosition(Long id);

    public abstract PositionDto updatePosition(PositionDto positionToUpdate, Long id);

}
