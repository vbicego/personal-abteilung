package de.evoila.humanResources;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CandidateControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    Flyway flyway;

    private static final String EXPECTED_LIST_CANDIDATES = "[\n" +
            "    {\n" +
            "        \"id\": 1,\n" +
            "        \"firstName\": \"Matt\",\n" +
            "        \"lastName\": \"Parker\",\n" +
            "        \"email\": \"matt.parker@gmail.com\",\n" +
            "        \"desiredSalary\": 4500\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 2,\n" +
            "        \"firstName\": \"Mary\",\n" +
            "        \"lastName\": \"Jane\",\n" +
            "        \"email\": \"mary.jane@gmail.com\",\n" +
            "        \"desiredSalary\": 7500\n" +
            "    },\n" +
            "    {\n" +
            "        \"id\": 3,\n" +
            "        \"firstName\": \"Tony\",\n" +
            "        \"lastName\": \"Stark\",\n" +
            "        \"email\": \"t.s@gmail.com\",\n" +
            "        \"desiredSalary\": 3500\n" +
            "    }\n" +
            "]";

    private static final String CANDIDATE_ID_1_HR = "    {\n" +
            "        \"id\": 1,\n" +
            "        \"firstName\": \"Matt\",\n" +
            "        \"lastName\": \"Parker\",\n" +
            "        \"email\": \"matt.parker@gmail.com\",\n" +
            "        \"desiredSalary\": 4500\n" +
            "    }";

    private static final String CANDIDATE_ID_1_NORMAL = "{\n" +
            "    \"firstName\": \"Matt\",\n" +
            "    \"lastName\": \"Parker\",\n" +
            "    \"email\": \"matt.parker@gmail.com\"\n" +
            "}";

    @BeforeEach
    public void init() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    void getAllCandidatesShouldReturnOkAndListOfCandidates() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(EXPECTED_LIST_CANDIDATES));
    }

    @Test
    public void findCandidateByIdNormalShouldReturnOkAndTheCorrespondentCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(CANDIDATE_ID_1_NORMAL));
    }

    @Test
    public void findCandidateByIdNormalShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void findCandidateByIdHrShouldReturnOkAndTheCorrespondentCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/hr/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(CANDIDATE_ID_1_HR));
    }

    @Test
    public void findCandidateByIdHrShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/candidate/hr/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void createCandidateShouldReturnOkAndTheCreatedCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/candidate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    {\n" +
                                "        \"firstName\": \"Vic\",\n" +
                                "        \"lastName\": \"Mic\",\n" +
                                "        \"email\": \"v.m@gmail.com\",\n" +
                                "        \"desiredSalary\": 4500\n" +
                                "    }"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 4,\n" +
                        "    \"firstName\": \"Vic\",\n" +
                        "    \"lastName\": \"Mic\",\n" +
                        "    \"email\": \"v.m@gmail.com\",\n" +
                        "    \"desiredSalary\": 4500\n" +
                        "}"));
    }

    @Test
    public void createCandidateShouldReturnBadRequestWhenTheDataIsInvalid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/candidate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    {\n" +
                                "        \"firstName\": \"Vic\",\n" +
                                "        \"lastName\": \"Mic\",\n" +
                                "        \"email\": \"v.mgmail.com\",\n" +
                                "        \"desiredSalary\": 4500\n" +
                                "    }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteCandidateShouldReturnOk() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/candidate/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteCandidateShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/candidate/5"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void updateCandidateShouldReturnOkAndTheUpdatedCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/candidate/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    {\n" +
                                "        \"firstName\": \"Marylane\",\n" +
                                "        \"lastName\": \"Janeckson\",\n" +
                                "        \"email\": \"mary.jane@gmail.com\",\n" +
                                "        \"desiredSalary\": 10500\n" +
                                "    }"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\n" +
                        "    \"id\": 2,\n" +
                        "    \"firstName\": \"Marylane\",\n" +
                        "    \"lastName\": \"Janeckson\",\n" +
                        "    \"email\": \"mary.jane@gmail.com\",\n" +
                        "    \"desiredSalary\": 10500\n" +
                        "}"));
    }

    @Test
    public void updateCandidateShouldReturnNotFoundWhenTheIdNotCorrespondToAnyCandidate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/candidate/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    {\n" +
                                "        \"firstName\": \"Vic\",\n" +
                                "        \"lastName\": \"Mic\",\n" +
                                "        \"email\": \"v.m@gmail.com\",\n" +
                                "        \"desiredSalary\": 4500\n" +
                                "    }"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Candidate with id: 5 not found."));
    }

    @Test
    public void updateCandidateShouldReturnBadRequestWhenTheDataIsInvalid() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .put("/candidate/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("    {\n" +
                                "        \"firstName\": \"Vic\",\n" +
                                "        \"lastName\": \"Mic\",\n" +
                                "        \"email\": \"v.mgmail.com\",\n" +
                                "        \"desiredSalary\": 4500\n" +
                                "    }"))
                .andExpect(status().isBadRequest());
    }

}
