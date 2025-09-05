package com.example.f1analysis.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.example.f1analysis.dto.TeamRequest;
import com.example.f1analysis.model.Team;
import com.example.f1analysis.service.TeamService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTeams() {
        Team team1 = new Team();
        Team team2 = new Team();
        List<Team> mockList = Arrays.asList(team1, team2);
        when(teamService.getAllTeams()).thenReturn(mockList);
        List<Team> result = controller.getAllTeams();
        assertThat(result).hasSize(2);
        verify(teamService, times(1)).getAllTeams();
    }

    @Test
    void testGetTeamById() {
        Team team = new Team();
        when(teamService.getTeamById(1)).thenReturn(team);
        Team result = controller.getTeamById(1);
        assertThat(result).isNotNull();
        verify(teamService, times(1)).getTeamById(1);
    }

    @Test
    void testCreateTeam() {
        TeamRequest request = new TeamRequest();
        Team team = new Team();
        when(teamService.saveTeam(request)).thenReturn(team);
        Team result = controller.createTeam(request);
        assertThat(result).isNotNull();
        verify(teamService, times(1)).saveTeam(request);
    }

    @Test
    void testUpdateTeam() {
        TeamRequest request = new TeamRequest();
        Team team = new Team();
        when(teamService.updateTeam(1, request)).thenReturn(team);
        Team result = controller.updateTeam(1, request);
        assertThat(result).isNotNull();
        verify(teamService, times(1)).updateTeam(1, request);
    }

    @Test
    void testDeleteTeamById() {
        doNothing().when(teamService).deleteTeamById(1);
        controller.deleteTeamById(1);
        verify(teamService, times(1)).deleteTeamById(1);
    }

    @Test
    void testFetchingCreate() {
        TeamRequest request = new TeamRequest();
        Team team = new Team();
        when(teamService.saveTeam(request)).thenReturn(team);
        Team created = controller.createTeam(request);
        when(teamService.getTeamById(1)).thenReturn(created);
        Team fetched = controller.getTeamById(1);
        assertThat(fetched).isEqualTo(created);
        verify(teamService, times(1)).saveTeam(request);
        verify(teamService, times(1)).getTeamById(1);
    }

    @Test
    void testFetchingUpdate() {
        TeamRequest request = new TeamRequest();
        Team updatedTeam = new Team();
        when(teamService.updateTeam(1, request)).thenReturn(updatedTeam);
        Team result = controller.updateTeam(1, request);
        when(teamService.getTeamById(1)).thenReturn(updatedTeam);
        Team fetched = controller.getTeamById(1);
        assertThat(result).isEqualTo(fetched);
        verify(teamService, times(1)).updateTeam(1, request);
        verify(teamService, times(1)).getTeamById(1);
    }

    @Test
    void testVerefiedDeleting() {
        doNothing().when(teamService).deleteTeamById(1);
        controller.deleteTeamById(1);
        when(teamService.getAllTeams()).thenReturn(Arrays.asList());
        List<Team> teams = controller.getAllTeams();
        assertThat(teams).isEmpty();
        verify(teamService, times(1)).deleteTeamById(1);
        verify(teamService, times(1)).getAllTeams();
    }
}
