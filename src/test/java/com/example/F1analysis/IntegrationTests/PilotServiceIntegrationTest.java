package com.example.F1analysis.IntegrationTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.f1analysis.F1AnalysisApplication;
import com.example.f1analysis.dto.PilotRequest;
import com.example.f1analysis.dto.TeamRequest;
import com.example.f1analysis.model.Pilot;
import com.example.f1analysis.model.Team;
import com.example.f1analysis.service.PilotService;
import com.example.f1analysis.service.TeamService;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = F1AnalysisApplication.class)
@ActiveProfiles("test")
@Testcontainers
public class PilotServiceIntegrationTest {

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
    private PilotService pilotService;

    @Autowired
    private TeamService teamService;

    private Team createTeam(String name) {
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
        Team team = createTeam("Mercedes");
        PilotRequest request = createPilotRequest("Lewis", "Hamilton", team.getTeamId());
        Pilot savedPilot = pilotService.savePilot(request);

        Pilot fetchedPilot = pilotService.getPilotById(savedPilot.getPilotId());
        assertThat(fetchedPilot.getFirstName()).isEqualTo("Lewis");
        assertThat(fetchedPilot.getTeam().getTeamName()).isEqualTo("Mercedes");
    }

    @Test
    void testUpdatePilot() {
        Team team = createTeam("Red Bull");
        Pilot pilot = pilotService.savePilot(createPilotRequest("Max", "Verstappen", team.getTeamId()));

        PilotRequest updateRequest = createPilotRequest("Max", "Updated", team.getTeamId());
        Pilot updatedPilot = pilotService.updatePilot(pilot.getPilotId(), updateRequest);

        assertThat(updatedPilot.getLastName()).isEqualTo("Updated");
    }

    @Test
    void testDeletePilot() {
        Team team = createTeam("Ferrari");
        Pilot pilot = pilotService.savePilot(createPilotRequest("Charles", "Leclerc", team.getTeamId()));

        pilotService.deletePilotById(pilot.getPilotId());
        List<Pilot> pilots = pilotService.getAllPilots();
        assertThat(pilots).extracting(Pilot::getFirstName).doesNotContain("Charles");
    }

    @Test
    void testGetAllPilots() {
        Team team = createTeam("McLaren");
        pilotService.savePilot(createPilotRequest("Lando", "Norris", team.getTeamId()));
        pilotService.savePilot(createPilotRequest("Daniel", "Ricciardo", team.getTeamId()));

        List<Pilot> pilots = pilotService.getAllPilots();
        assertThat(pilots).extracting(Pilot::getFirstName).contains("Lando", "Daniel");
    }
}
