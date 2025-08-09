package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.f1_team;
import com.example.demo.service.f1_teamService;

@RestController
@RequestMapping("/teams")
public class f1_teamController {

    private final f1_teamService teamService;

    public f1_teamController(f1_teamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<f1_team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public f1_team createTeam(@RequestBody f1_team team) {
        return teamService.saveTeam(team);
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
