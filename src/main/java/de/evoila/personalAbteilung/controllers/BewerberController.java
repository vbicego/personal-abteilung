package de.evoila.personalAbteilung.controllers;

import de.evoila.personalAbteilung.dtos.BewerberDto;
import de.evoila.personalAbteilung.services.BewerberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bewerber")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BewerberController {

    private BewerberService bewerberService;

    @GetMapping
    public List<BewerberDto> getAllBewerber() {
        return bewerberService.getAllBewerber();
    }

    @GetMapping("/{id}")
    public BewerberDto findBewerberById(@PathVariable Long id) {
        return bewerberService.findBewerberById(id);
    }

    @PostMapping
    public BewerberDto createBewerber(@RequestBody @Valid BewerberDto bewerberToCreate) {
        return bewerberService.createBewerber(bewerberToCreate);
    }

    @DeleteMapping("/{id}")
    public void deleteBewerber(@PathVariable Long id) {
        bewerberService.deleteBewerber(id);
    }

    @PutMapping("/{id}")
    public BewerberDto updateBewerber(@RequestBody @Valid BewerberDto bewerberToUpdate, @PathVariable Long id) {
        return bewerberService.updateBewerber(bewerberToUpdate, id);
    }

}
