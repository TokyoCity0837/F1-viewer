package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.repository.f1_teamRepository;
import com.example.demo.model.f1_team;

@Service
public class f1_teamService {

    private final f1_teamRepository teamRepository;

    public f1_teamService(f1_teamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<f1_team> getAllTeams() {
        return teamRepository.findAll();
    }

    public f1_team saveTeam(f1_team team) {
        return teamRepository.save(team);
    }

    public f1_team getTeamById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    public void deleteTeamById(Integer id) {
        teamRepository.deleteById(id);
    }
}
