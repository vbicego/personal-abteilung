package de.evoila.personalAbteilung.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
public class Bewerber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    private Stelle stelle;

}
