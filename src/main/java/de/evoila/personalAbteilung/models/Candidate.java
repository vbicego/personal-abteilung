package de.evoila.personalAbteilung.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@RequiredArgsConstructor
@Setter
@Getter
@ToString
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Long desiredSalary;

    public Candidate(String firstName, String lastName, String email, Long desiredSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.desiredSalary = desiredSalary;
    }
}
