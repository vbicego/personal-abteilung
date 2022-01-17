package de.evoila.personalAbteilung.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.evoila.personalAbteilung.dtos.CandidateDto;
import de.evoila.personalAbteilung.services.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping
    public List<CandidateDto> getAllCandidates() {
        return candidateService.getAllCandidates();
    }

    @GetMapping("/{id}")
    public String findCandidateById(@PathVariable Long id) throws JsonProcessingException {
        return candidateService.findCandidateByIdNormal(id);
    }

    @GetMapping("/hr/{id}")
    public String findCandidateByIdHr(@PathVariable Long id) throws JsonProcessingException {
        return candidateService.findCandidateByIdHr(id);
    }

    @PostMapping
    public CandidateDto createCandidate(@RequestBody @Valid CandidateDto candidateToCreate) {
        return candidateService.createCandidate(candidateToCreate);
    }

    @DeleteMapping("/{id}")
    public void deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
    }

    @PutMapping("/{id}")
    public CandidateDto updateCandidate(@RequestBody @Valid CandidateDto candidateToUpdate, @PathVariable Long id) {
        return candidateService.updateCandidate(candidateToUpdate, id);
    }

}
