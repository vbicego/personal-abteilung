package de.evoila.personalAbteilung.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PositionDto {

    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Min(value = 1584)
    private Long maxSalary;

    List<CandidateDto> candidateDtoList;

}
