package de.evoila.personalAbteilung.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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

    public PositionDto(String title, String description, Long maxSalary) {
        this.title = title;
        this.description = description;
        this.maxSalary = maxSalary;
    }
}
