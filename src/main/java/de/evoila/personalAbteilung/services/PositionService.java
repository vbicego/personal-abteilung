package de.evoila.personalAbteilung.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.evoila.personalAbteilung.PositionViews;
import de.evoila.personalAbteilung.dtos.PositionDto;
import de.evoila.personalAbteilung.exceptions.PositionNotFoundException;
import de.evoila.personalAbteilung.models.Position;
import de.evoila.personalAbteilung.repositories.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PositionService {

    private final PositionRepository positionRepository;
    private final ModelMapper modelMapper;
    private final ObjectMapper mapper;

    public List<PositionDto> getAllPositions() {
        List<Position> positionList = positionRepository.findAll();
        return positionList.stream()
                .map(position -> modelMapper.map(position, PositionDto.class))
                .collect(Collectors.toList());
    }

    public String findPositionById(Long id) throws JsonProcessingException {
        Position foundPosition = positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
        return mapper.writerWithView(PositionViews.Normal.class).writeValueAsString(modelMapper.map(foundPosition, PositionDto.class));
    }

    public String findPositionByIdHr(Long id) throws JsonProcessingException {
        Position foundPosition = positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
        return mapper.writerWithView(PositionViews.Hr.class).writeValueAsString(modelMapper.map(foundPosition, PositionDto.class));
    }

    public PositionDto createPosition(PositionDto positionToCreate) {
        Position createdPosition = positionRepository.save(modelMapper.map(positionToCreate, Position.class));
        return modelMapper.map(createdPosition, PositionDto.class);
    }

    public void deletePosition(Long id) {
        positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));
        positionRepository.deleteById(id);
    }

    public PositionDto updatePosition(PositionDto positionToUpdate, Long id) {
        Position foundPosition = positionRepository.findById(id).orElseThrow(() -> new PositionNotFoundException(id));

        foundPosition.setTitle(positionToUpdate.getTitle());
        foundPosition.setDescription(positionToUpdate.getDescription());
        foundPosition.setMaxSalary(positionToUpdate.getMaxSalary());

        Position updatedPosition = positionRepository.save(foundPosition);
        return modelMapper.map(updatedPosition, PositionDto.class);
    }

}
