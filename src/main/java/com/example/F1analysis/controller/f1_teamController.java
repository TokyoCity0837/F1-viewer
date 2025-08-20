package com.example.F1analysis.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.F1analysis.dto.TeamRequest;
import com.example.F1analysis.model.f1_team;
import com.example.F1analysis.service.f1_teamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/teams")
public class f1_teamController {

    private final f1_teamService teamService;

    public f1_teamController(f1_teamService teamService) {
        this.teamService = teamService;
    }


    @PutMapping("/{id}")
    public f1_team updateTeam(@PathVariable Integer id, @Valid @RequestBody TeamRequest request) {
    return teamService.updateTeam(id, request);
    }


    @GetMapping
    public List<f1_team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public f1_team createTeam(@Valid @RequestBody TeamRequest request) {
        return teamService.saveTeam(request);
    }

    @GetMapping("/{id}")
    public f1_team getTeamById(@PathVariable Integer id) {
        return teamService.getTeamById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTeamById(@PathVariable Integer id) {
        teamService.deleteTeamById(id);
    }
}
