package com.example.F1analysis.IntegrationTests;

import com.example.F1analysis.F1AnalysisApplication;
import com.example.F1analysis.dto.PilotRequest;
import com.example.F1analysis.dto.TeamRequest;
import com.example.F1analysis.model.f1_pilot;
import com.example.F1analysis.model.f1_team;
import com.example.F1analysis.service.f1_pilotService;
import com.example.F1analysis.service.f1_teamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = F1AnalysisApplication.class)
@ActiveProfiles("test")
public class f1_pilotServiceIntegrationTest {

    @Autowired
    private f1_pilotService pilotService;

    @Autowired
    private f1_teamService teamService;

    @Test
    void testSavePilotViaService() {
        TeamRequest teamRequest = new TeamRequest();
        teamRequest.setTeamName("Ferrari");
        teamRequest.setBaseCountry("Italy");
        teamRequest.setTeamPrinciple("Fred Vasseur");
        teamRequest.setTeamFoundation(1929);
        teamRequest.setTeamChampionships(16);

        f1_team savedTeam = teamService.saveTeam(teamRequest);

        PilotRequest pilotRequest = new PilotRequest();
        pilotRequest.setFirstName("Charles");
        pilotRequest.setLastName("Leclerc");
        pilotRequest.setCountry("Monaco");
        pilotRequest.setBirthDate(LocalDate.of(1997, 10, 16));
        pilotRequest.setCarNumber(16);
        pilotRequest.setTeamId(savedTeam.getTeamId());

        f1_pilot savedPilot = pilotService.savePilot(pilotRequest);

        assertThat(savedPilot.getPilotId()).isNotNull();
        assertThat(savedPilot.getFirstName()).isEqualTo("Charles");
        assertThat(savedPilot.getLastName()).isEqualTo("Leclerc");
        assertThat(savedPilot.getNationality()).isEqualTo("Monaco");
        assertThat(savedPilot.getBirthDate()).isEqualTo(LocalDate.of(1997, 10, 16));
        assertThat(savedPilot.getCarNumber()).isEqualTo(16);
        assertThat(savedPilot.getTeam().getTeamName()).isEqualTo("Ferrari");
    }
}
