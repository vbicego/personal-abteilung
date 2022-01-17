package de.evoila.personalAbteilung.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import de.evoila.personalAbteilung.PositionViews;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PositionDto {

    @JsonView(PositionViews.Hr.class)
    private Long id;

    @NotNull
    @JsonView({PositionViews.Normal.class, PositionViews.Hr.class})
    private String title;

    @NotNull
    @JsonView({PositionViews.Normal.class, PositionViews.Hr.class})
    private String description;

    @NotNull
    @Min(value = 1584)
    @JsonView(PositionViews.Hr.class)
    private Long maxSalary;

    public PositionDto(String title, String description, Long maxSalary) {
        this.title = title;
        this.description = description;
        this.maxSalary = maxSalary;
    }
}
