package de.evoila.humanResources.dtos;

import com.fasterxml.jackson.annotation.JsonView;
import de.evoila.humanResources.models.Candidate;
import de.evoila.humanResources.views.CandidateViews;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CandidateDto {

    @JsonView(CandidateViews.Hr.class)
    private Long id;

    @NotNull(message = "First name could not be empty")
    @JsonView(CandidateViews.Normal.class)
    private String firstName;

    @NotNull(message = "Last name could not be empty")
    @JsonView(CandidateViews.Normal.class)
    private String lastName;

    @Email(message = "This ist not a valid email")
    @JsonView(CandidateViews.Normal.class)
    private String email;

    @NotNull(message = "Desired salary could not be empty")
    @Min(value = 1584, message = "Desired salary must be at least 1584,00")
    @JsonView(CandidateViews.Hr.class)
    private Long desiredSalary;

    public Candidate convertDtoToEntity (){
        return new ModelMapper().map(this, Candidate.class);
    }

    public CandidateDto(String firstName, String lastName, String email, Long desiredSalary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.desiredSalary = desiredSalary;
    }

}
