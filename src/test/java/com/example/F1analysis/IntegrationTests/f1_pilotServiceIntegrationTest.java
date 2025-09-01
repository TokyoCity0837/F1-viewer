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
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = F1AnalysisApplication.class)
@ActiveProfiles("test")
@Testcontainers
public class f1_pilotServiceIntegrationTest {

    @Container
    @SuppressWarnings("resource")
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36")
        .withDatabaseName("f1_test_db")
        .withUsername("testuser")
        .withPassword("testpass")
        .withCreateContainerCmdModifier(cmd -> cmd.withPlatform("linux/amd64"));

    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
    }

    @Autowired
    private f1_pilotService pilotService;

    @Autowired
    private f1_teamService teamService;

    private f1_team createTeam(String name) {
        TeamRequest request = new TeamRequest();
        request.setTeamName(name);
        request.setBaseCountry("UK");
        request.setTeamPrinciple("Principle");
        request.setTeamFoundation(1960);
        request.setTeamChampionships(8);
        return teamService.saveTeam(request);
    }

    private PilotRequest createPilotRequest(String firstName, String lastName, int teamId) {
        PilotRequest request = new PilotRequest();
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setCountry("UK");
        request.setBirthDate(LocalDate.of(2000, 1, 1));
        request.setCarNumber(44);
        request.setTeamId(teamId);
        return request;
    }

    @Test
    void testCreateAndGetPilot() {
        f1_team team = createTeam("Mercedes");
        PilotRequest request = createPilotRequest("Lewis", "Hamilton", team.getTeamId());
        f1_pilot savedPilot = pilotService.savePilot(request);

        f1_pilot fetchedPilot = pilotService.getPilotById(savedPilot.getPilotId());
        assertThat(fetchedPilot.getFirstName()).isEqualTo("Lewis");
        assertThat(fetchedPilot.getTeam().getTeamName()).isEqualTo("Mercedes");
    }

    @Test
    void testUpdatePilot() {
        f1_team team = createTeam("Red Bull");
        f1_pilot pilot = pilotService.savePilot(createPilotRequest("Max", "Verstappen", team.getTeamId()));

        PilotRequest updateRequest = createPilotRequest("Max", "Updated", team.getTeamId());
        f1_pilot updatedPilot = pilotService.updatePilot(pilot.getPilotId(), updateRequest);

        assertThat(updatedPilot.getLastName()).isEqualTo("Updated");
    }

    @Test
    void testDeletePilot() {
        f1_team team = createTeam("Ferrari");
        f1_pilot pilot = pilotService.savePilot(createPilotRequest("Charles", "Leclerc", team.getTeamId()));

        pilotService.deletePilotById(pilot.getPilotId());
        List<f1_pilot> pilots = pilotService.getAllPilots();
        assertThat(pilots).extracting(f1_pilot::getFirstName).doesNotContain("Charles");
    }

    @Test
    void testGetAllPilots() {
        f1_team team = createTeam("McLaren");
        pilotService.savePilot(createPilotRequest("Lando", "Norris", team.getTeamId()));
        pilotService.savePilot(createPilotRequest("Daniel", "Ricciardo", team.getTeamId()));

        List<f1_pilot> pilots = pilotService.getAllPilots();
        assertThat(pilots).extracting(f1_pilot::getFirstName).contains("Lando", "Daniel");
    }
}
