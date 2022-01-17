package de.evoila.personalAbteilung.models;

import com.fasterxml.jackson.annotation.JsonView;
import de.evoila.personalAbteilung.PositionViews;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    @Min(value = 1584)
    private Long maxSalary;

    public Position(String title, String description, Long maxSalary) {
        this.title = title;
        this.description = description;
        this.maxSalary = maxSalary;
    }
}
