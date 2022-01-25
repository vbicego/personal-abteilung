package de.evoila.humanResources.models;

import de.evoila.humanResources.dtos.CandidateDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long desiredSalary;

    public CandidateDto convertEntityToDto() {
        return new ModelMapper().map(this, CandidateDto.class);
    }

}
