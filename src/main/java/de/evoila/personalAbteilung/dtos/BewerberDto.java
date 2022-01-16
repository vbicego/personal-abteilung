package de.evoila.personalAbteilung.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class BewerberDto {

    private Long id;

    @NotNull
    private String vorName;

    @NotNull
    private String nachName;

    @Email
    private String email;

    @NotNull
    @Min(value = 1584)
    private Long wunschGehalt;

    @NotNull
    private StelleDto stelleDto;

}
