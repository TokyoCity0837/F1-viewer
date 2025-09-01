package com.example.f1analysis.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.f1analysis.dto.TeamRequest;
import com.example.f1analysis.model.Team;
import com.example.f1analysis.service.TeamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Team getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Team createTeam(@Valid @RequestBody TeamRequest request) {
        return teamService.saveTeam(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Team updateTeam(@PathVariable Integer id, @Valid @RequestBody TeamRequest request) {
        return teamService.updateTeam(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable Integer id) {
        teamService.deleteTeamById(id);
    }
}
