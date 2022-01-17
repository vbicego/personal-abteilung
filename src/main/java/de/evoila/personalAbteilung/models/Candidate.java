package de.evoila.personalAbteilung.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne(cascade = CascadeType.ALL)
    private Position position;

    public Candidate(String firstName, String lastName, String email, Long desiredSalary, Position position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.desiredSalary = desiredSalary;
        this.position = position;
    }
}
