package de.evoila.personalAbteilung.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class StelleDto {

    private Long id;

    @NotNull
    private String titel;

    @NotNull
    private String beschreibung;

    @NotNull
    @Min(value = 1584)
    private Long maxGehalt;

    List<BewerberDto> bewerberList;

}
