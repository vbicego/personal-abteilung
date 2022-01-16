package de.evoila.personalAbteilung.controllers;

import de.evoila.personalAbteilung.dtos.StelleDto;
import de.evoila.personalAbteilung.services.StelleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/stellen")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StelleController {

    private final StelleService stelleService;

    @GetMapping
    public List<StelleDto> getAllStellen() {
        return stelleService.getAllStellen();
    }

    @GetMapping("/{id}")
    public StelleDto findStelleById(@PathVariable Long id) {
        return stelleService.findStelleById(id);
    }

    @PostMapping
    public StelleDto createStelle(@RequestBody @Valid StelleDto stelleToCreate) {
        return stelleService.createStelle(stelleToCreate);
    }

    @DeleteMapping("/{id}")
    public void deleteStelle(@PathVariable Long id) {
        stelleService.deleteStelle(id);
    }

    @PutMapping("/{id}")
    public StelleDto updateStelle(@RequestBody @Valid StelleDto stelleToUpdate, @PathVariable Long id) {
        return stelleService.updateStelle(stelleToUpdate, id);
    }

}
