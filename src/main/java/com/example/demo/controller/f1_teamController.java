package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.f1_team;
import com.example.demo.service.f1_teamService;
import com.example.demo.TeamRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/teams")
public class f1_teamController {

    private final f1_teamService teamService;


    @PutMapping("/{id}")
    public f1_team updateTeam(@PathVariable Integer id, @Valid @RequestBody TeamRequest request) {
    return teamService.updateTeam(id, request);
}


    public f1_teamController(f1_teamService teamService) {
        this.teamService = teamService;
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
