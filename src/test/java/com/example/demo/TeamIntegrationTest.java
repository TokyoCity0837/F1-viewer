package com.example.demo;

import com.example.demo.model.f1_team;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TeamIntegrationTest {

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

    @Test
    void testCreateAndGetTeam() throws Exception {
        createTeam("Mercedes", "Germany", "Toto Wolff", 1954, 8);

        mockMvc.perform(get("/teams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].teamName").value("Mercedes"))
                .andExpect(jsonPath("$[0].baseCountry").value("Germany"))
                .andExpect(jsonPath("$[0].teamPrinciple").value("Toto Wolff"))
                .andExpect(jsonPath("$[0].teamFoundation").value(1954))
                .andExpect(jsonPath("$[0].teamChampionships").value(8));
    }

    @Test
    void testUpdateTeam() throws Exception {
        createTeam("Red Bull Racing", "Austria", "Christian Horner", 1984, 6);

        f1_team updatedTeam = new f1_team("Red Bull Racing", "Austria", "Christian Horner", 1984, 7);
        mockMvc.perform(put("/teams/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTeam)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/teams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.teamChampionships").value(7));
    }

    @Test
    void testDeleteTeam() throws Exception {
        createTeam("Ferrari", "Italy", "Mattia Binotto", 1929, 16);

        mockMvc.perform(delete("/teams/1"))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/teams/1"))
                .andExpect(status().isNotFound());
    }
}
