package de.evoila.personalAbteilung.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

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
