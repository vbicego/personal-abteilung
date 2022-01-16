package de.evoila.personalAbteilung.services;

import de.evoila.personalAbteilung.dtos.StelleDto;
import de.evoila.personalAbteilung.exceptions.StelleNotFoundException;
import de.evoila.personalAbteilung.models.Stelle;
import de.evoila.personalAbteilung.repositories.StelleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StelleService {

    private final StelleRepository stelleRepository;
    private final ModelMapper modelMapper;

    public List<StelleDto> getAllStellen() {
        List<Stelle> stelleList = stelleRepository.findAll();
        return stelleList.stream()
                .map(stelle -> modelMapper.map(stelle, StelleDto.class))
                .collect(Collectors.toList());
    }

    public StelleDto findStelleById(Long id) {
        Stelle foundStelle = stelleRepository.findById(id).orElseThrow(() -> new StelleNotFoundException(id));
        return modelMapper.map(foundStelle, StelleDto.class);
    }

    public StelleDto createStelle(StelleDto stelleToCreate) {
        Stelle createdStelle = stelleRepository.save(modelMapper.map(stelleToCreate, Stelle.class));
        return modelMapper.map(createdStelle, StelleDto.class);
    }

    public void deleteStelle(Long id) {
        stelleRepository.findById(id).orElseThrow(() -> new StelleNotFoundException(id));
        stelleRepository.deleteById(id);
    }

    public StelleDto updateStelle(StelleDto stelleToUpdate, Long id) {
        Stelle foundStelle = stelleRepository.findById(id).orElseThrow(() -> new StelleNotFoundException(id));

        foundStelle.setTitel(stelleToUpdate.getTitel());
        foundStelle.setBeschreibung(stelleToUpdate.getBeschreibung());
        foundStelle.setMaxGehalt(stelleToUpdate.getMaxGehalt());
        foundStelle.setBewerberList(stelleToUpdate.getBewerberList());

        Stelle updatedStelle = stelleRepository.save(foundStelle);
        return modelMapper.map(updatedStelle, StelleDto.class);
    }

}
