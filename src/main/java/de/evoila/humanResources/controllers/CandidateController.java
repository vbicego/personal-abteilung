package de.evoila.humanResources.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import de.evoila.humanResources.dtos.CandidateDto;
import de.evoila.humanResources.services.CandidateService;
import de.evoila.humanResources.views.CandidateViews;
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

    @JsonView(CandidateViews.Normal.class)
    @GetMapping("/{id}")
    public CandidateDto findCandidateById(@PathVariable Long id) {
        return candidateService.findCandidateById(id);
    }

    @JsonView(CandidateViews.Hr.class)
    @GetMapping("/hr/{id}")
    public CandidateDto findCandidateByIdHr(@PathVariable Long id) {
        return candidateService.findCandidateById(id);
    }

    @PostMapping
    public CandidateDto createCandidate(@RequestBody @Valid CandidateDto candidateToCreateDto) {
        return candidateService.createCandidate(candidateToCreateDto);
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
