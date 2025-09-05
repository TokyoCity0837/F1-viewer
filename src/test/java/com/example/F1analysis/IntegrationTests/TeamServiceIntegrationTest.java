package com.example.f1analysis.IntegrationTests;

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
import com.example.f1analysis.dto.TeamRequest;
import com.example.f1analysis.model.Team;
import com.example.f1analysis.service.TeamService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = F1AnalysisApplication.class)
@ActiveProfiles("test")
@Testcontainers
public class TeamServiceIntegrationTest {

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
    private TeamService teamService;

    @Test
    void testSaveTeamViaService() {
        TeamRequest request = new TeamRequest();
        request.setTeamName("Mercedes");
        request.setBaseCountry("UK");
        request.setTeamPrinciple("Toto Wolff");
        request.setTeamFoundation(1954);
        request.setTeamChampionships(9);

        Team savedTeam = teamService.saveTeam(request);

        assertThat(savedTeam.getTeamId()).isNotNull();
        assertThat(savedTeam.getTeamName()).isEqualTo("Mercedes");
        assertThat(savedTeam.getCountry()).isEqualTo("UK");
        assertThat(savedTeam.getTeamPrinciple()).isEqualTo("Toto Wolff");
        assertThat(savedTeam.getTeamFoundation()).isEqualTo(1954);
        assertThat(savedTeam.getTeamChampionships()).isEqualTo(9);
    }

    @Test
    void testUpdateTeamViaService() {
        TeamRequest request = new TeamRequest();
        request.setTeamName("Red Bull");
        request.setBaseCountry("Austria");
        request.setTeamPrinciple("Christian Horner");
        request.setTeamFoundation(1984);
        request.setTeamChampionships(6);

        Team savedTeam = teamService.saveTeam(request);

        TeamRequest updateRequest = new TeamRequest();
        updateRequest.setTeamName("Red Bull Racing");
        updateRequest.setBaseCountry("Austria");
        updateRequest.setTeamPrinciple("Christian Horner");
        updateRequest.setTeamFoundation(1984);
        updateRequest.setTeamChampionships(7);

        Team updatedTeam = teamService.updateTeam(savedTeam.getTeamId(), updateRequest);

        assertThat(updatedTeam.getTeamName()).isEqualTo("Red Bull Racing");
        assertThat(updatedTeam.getTeamChampionships()).isEqualTo(7);
    }

    @Test
    void testDeleteTeamViaService() {
        TeamRequest request = new TeamRequest();
        request.setTeamName("Alfa Romeo");
        request.setBaseCountry("Switzerland");
        request.setTeamPrinciple("Frédéric Vasseur");
        request.setTeamFoundation(1911);
        request.setTeamChampionships(2);

        Team savedTeam = teamService.saveTeam(request);

        teamService.deleteTeamById(savedTeam.getTeamId());

        assertThat(teamService.getAllTeams()).doesNotContain(savedTeam);
    }
}
