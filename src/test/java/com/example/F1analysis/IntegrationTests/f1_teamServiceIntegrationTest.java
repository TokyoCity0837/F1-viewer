package com.example.F1analysis.IntegrationTests;

import com.example.F1analysis.F1AnalysisApplication;
import com.example.F1analysis.dto.TeamRequest;
import com.example.F1analysis.model.f1_team;
import com.example.F1analysis.service.f1_teamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = F1AnalysisApplication.class)
@ActiveProfiles("test")
public class f1_teamServiceIntegrationTest {

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
        assertThat(savedTeam.getCountry()).isEqualTo("Italy");
        assertThat(savedTeam.getTeamPrinciple()).isEqualTo("Fred Vasseur");
        assertThat(savedTeam.getTeamFoundation()).isEqualTo(1929);
        assertThat(savedTeam.getTeamChampionships()).isEqualTo(16);
    }
}
