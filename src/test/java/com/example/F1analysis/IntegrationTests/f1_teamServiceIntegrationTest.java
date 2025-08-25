package com.example.F1analysis.IntegrationTests;

import com.example.F1analysis.F1AnalysisApplication;
import com.example.F1analysis.dto.TeamRequest;
import com.example.F1analysis.model.f1_team;
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

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = F1AnalysisApplication.class)
@ActiveProfiles("test")
@Testcontainers
public class f1_teamServiceIntegrationTest {

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
    private f1_teamService teamService;

    @Test
    void testSaveTeamViaService() {
        TeamRequest request = new TeamRequest();
        request.setTeamName("Ferrari");
        request.setBaseCountry("Italy");
        request.setTeamPrinciple("Fred Vasseur");
        request.setTeamFoundation(1929);
        request.setTeamChampionships(16);

        f1_team savedTeam = teamService.saveTeam(request);

        assertThat(savedTeam.getTeamId()).isNotNull();
        assertThat(savedTeam.getTeamName()).isEqualTo("Ferrari");
    }
}
