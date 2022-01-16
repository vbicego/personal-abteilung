package de.evoila.personalAbteilung.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class CandidateDto {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    private String email;

    @NotNull
    @Min(value = 1584)
    private Long desiredSalary;

    @NotNull
    private PositionDto positionDto;

}
