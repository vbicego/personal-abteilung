package de.evoila.personalAbteilung.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.evoila.personalAbteilung.dtos.PositionDto;
import de.evoila.personalAbteilung.services.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/position")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PositionController {

    private final PositionService positionService;

    @GetMapping
    public List<PositionDto> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/{id}")
    public String findPositionById(@PathVariable Long id) throws JsonProcessingException {
        return positionService.findPositionById(id);
    }

    @GetMapping("/hr/{id}")
    public String findPositionByIdHr(@PathVariable Long id) throws JsonProcessingException {
        return positionService.findPositionByIdHr(id);
    }

    @PostMapping
    public PositionDto createPosition(@RequestBody @Valid PositionDto positionToCreate) {
        return positionService.createPosition(positionToCreate);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }

    @PutMapping("/{id}")
    public PositionDto updatePosition(@RequestBody @Valid PositionDto positionToUpdate, @PathVariable Long id) {
        return positionService.updatePosition(positionToUpdate, id);
    }

}
