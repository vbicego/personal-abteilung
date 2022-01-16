package de.evoila.personalAbteilung.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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
public class Stelle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String titel;

    @NotNull
    private String beschreibung;

    @NotNull
    @Min(value = 1584)
    private Long maxGehalt;

    List<Bewerber> bewerberList;
}
