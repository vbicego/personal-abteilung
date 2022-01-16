package de.evoila.personalAbteilung.services;

import de.evoila.personalAbteilung.dtos.BewerberDto;
import de.evoila.personalAbteilung.exceptions.BewerberNotFoundException;
import de.evoila.personalAbteilung.models.Bewerber;
import de.evoila.personalAbteilung.models.Stelle;
import de.evoila.personalAbteilung.repositories.BewerberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BewerberService {

    private final BewerberRepository bewerberRepository;
    private final ModelMapper modelMapper;

    public List<BewerberDto> getAllBewerber() {
        List<Bewerber> bewerberList = bewerberRepository.findAll();
        return bewerberList.stream()
                .map(bewerber -> modelMapper.map(bewerber, BewerberDto.class))
                .collect(Collectors.toList());
    }

    public BewerberDto findBewerberById(Long id) {
        Bewerber foundBewerber = bewerberRepository.findById(id).orElseThrow(() -> new BewerberNotFoundException(id));
        return modelMapper.map(foundBewerber, BewerberDto.class);
    }

    public BewerberDto createBewerber(BewerberDto bewerberToCreate) {
        Bewerber createdBewerber = bewerberRepository.save(modelMapper.map(bewerberToCreate, Bewerber.class));
        return modelMapper.map(createdBewerber, BewerberDto.class);
    }

    public void deleteBewerber(Long id) {
        bewerberRepository.findById(id).orElseThrow(() -> new BewerberNotFoundException(id));
        bewerberRepository.deleteById(id);
    }

    public BewerberDto updateBewerber(BewerberDto bewerberToUpdate, Long id) {
        Bewerber foundBewerber = bewerberRepository.findById(id).orElseThrow(() -> new BewerberNotFoundException(id));

        foundBewerber.setVorName(bewerberToUpdate.getVorName());
        foundBewerber.setNachName(bewerberToUpdate.getNachName());
        foundBewerber.setEmail(bewerberToUpdate.getEmail());
        foundBewerber.setWunschGehalt(bewerberToUpdate.getWunschGehalt());
        foundBewerber.setStelle(modelMapper.map(bewerberToUpdate.getStelleDto(), Stelle.class));

        Bewerber updatedBewerber = bewerberRepository.save(foundBewerber);
        return modelMapper.map(updatedBewerber, BewerberDto.class);
    }

}

