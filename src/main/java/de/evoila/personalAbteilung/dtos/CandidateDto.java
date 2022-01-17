package de.evoila.personalAbteilung.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
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

    public CandidateDto(String firstName, String lastName, String email, Long desiredSalary, PositionDto positionDto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.desiredSalary = desiredSalary;
        this.positionDto = positionDto;
    }

}
