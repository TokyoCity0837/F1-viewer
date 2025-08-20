package com.example.F1analysis.controller;

import com.example.F1analysis.dto.TeamRequest;
import com.example.F1analysis.model.f1_team;
import com.example.F1analysis.service.f1_teamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class f1_teamControllerTest {

    @Mock
    private f1_teamService teamService; // мокируем сервис

    @InjectMocks
    private f1_teamController controller; // контроллер для теста

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // инициализация моков
    }

    @Test
    void testGetAllTeams() {
        f1_team team1 = new f1_team();
        f1_team team2 = new f1_team();
        List<f1_team> mockList = Arrays.asList(team1, team2);

        when(teamService.getAllTeams()).thenReturn(mockList);

        List<f1_team> result = controller.getAllTeams();

        assertThat(result).hasSize(2);
        verify(teamService, times(1)).getAllTeams();
    }

    @Test
    void testGetTeamById() {
        f1_team team = new f1_team();
        when(teamService.getTeamById(1)).thenReturn(team);

        f1_team result = controller.getTeamById(1);

        assertThat(result).isNotNull();
        verify(teamService, times(1)).getTeamById(1);
    }

    @Test
    void testCreateTeam() {
        TeamRequest request = new TeamRequest();
        f1_team team = new f1_team();
        when(teamService.saveTeam(request)).thenReturn(team);

        f1_team result = controller.createTeam(request);

        assertThat(result).isNotNull();
        verify(teamService, times(1)).saveTeam(request);
    }

    @Test
    void testUpdateTeam() {
        TeamRequest request = new TeamRequest();
        f1_team team = new f1_team();
        when(teamService.updateTeam(1, request)).thenReturn(team);

        f1_team result = controller.updateTeam(1, request);

        assertThat(result).isNotNull();
        verify(teamService, times(1)).updateTeam(1, request);
    }

    @Test
    void testDeleteTeamById() {
        doNothing().when(teamService).deleteTeamById(1);

        controller.deleteTeamById(1);

        verify(teamService, times(1)).deleteTeamById(1);
    }
}
