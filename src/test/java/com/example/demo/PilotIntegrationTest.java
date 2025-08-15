package com.example.demo;

import com.example.demo.model.f1_pilot;
import com.example.demo.model.f1_team;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PilotIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private f1_team createTeam(String name, String country, String principal, int foundation, int championships) throws Exception {
        f1_team team = new f1_team(name, country, principal, foundation, championships);
        mockMvc.perform(post("/teams")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(team)))
                .andExpect(status().isCreated());
        return team;
    }

    private f1_pilot createPilot(String firstName, String lastName, String nationality, Date birthDate, int number, f1_team team) throws Exception {
        f1_pilot pilot = new f1_pilot(firstName, lastName, nationality, birthDate, number, team);
        mockMvc.perform(post("/pilots")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pilot)))
                .andExpect(status().isCreated());
        return pilot;
    }

    @Test
    void testCreateAndGetPilot() throws Exception {
        f1_team team = createTeam("Mercedes", "Germany", "Toto Wolff", 1954, 8);
        createPilot("Lewis", "Hamilton", "UK", Date.valueOf("1985-01-07"), 44, team);

        mockMvc.perform(get("/pilots"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Lewis"))
                .andExpect(jsonPath("$[0].lastName").value("Hamilton"))
                .andExpect(jsonPath("$[0].nationality").value("UK"))
                .andExpect(jsonPath("$[0].number").value(44))
                .andExpect(jsonPath("$[0].team.teamName").value("Mercedes"));
    }

    @Test
    void testUpdatePilot() throws Exception {
        f1_team team = createTeam("Red Bull Racing", "Austria", "Christian Horner", 1984, 6);
        createPilot("Max", "Verstappen", "Netherlands", Date.valueOf("1997-09-30"), 33, team);

        f1_pilot updatedPilot = new f1_pilot("Max", "Verstappen", "Netherlands", Date.valueOf("1997-09-30"), 34, team);
        mockMvc.perform(put("/pilots/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedPilot)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/pilots/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(34));
    }

    @Test
    void testDeletePilot() throws Exception {
        f1_team team = createTeam("Ferrari", "Italy", "Mattia Binotto", 1929, 16);
        createPilot("Charles", "Leclerc", "Monaco", Date.valueOf("1997-10-16"), 16, team);

        mockMvc.perform(delete("/pilots/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/pilots/1"))
                .andExpect(status().isNotFound());
    }
}
