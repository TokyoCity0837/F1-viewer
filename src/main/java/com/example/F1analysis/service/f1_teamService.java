package com.example.F1analysis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import com.example.F1analysis.model.f1_team;
import com.example.F1analysis.dto.TeamRequest;
import com.example.F1analysis.repository.f1_teamRepository;

@Service
public class f1_teamService {

    private final f1_teamRepository teamRepository;

    public f1_teamService(f1_teamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<f1_team> getAllTeams() {
        return teamRepository.findAll();
    }

    public f1_team getTeamById(Integer id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Команда с ID " + id + " не найдена"));
    }

    public f1_team saveTeam(TeamRequest request) {
        f1_team team = mapToEntity(new f1_team(), request);
        return teamRepository.save(team);
    }

    public f1_team updateTeam(Integer id, TeamRequest request) {
        f1_team existingTeam = getTeamById(id);
        f1_team updatedTeam = mapToEntity(existingTeam, request);
        return teamRepository.save(updatedTeam);
    }

    public void deleteTeamById(Integer id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Команда с ID " + id + " не найдена");
        }
        teamRepository.deleteById(id);
    }

    private f1_team mapToEntity(f1_team team, TeamRequest request) {
        team.setTeamName(request.getTeamName());
        team.setCountry(request.getBaseCountry());
        team.setTeamPrinciple(request.getTeamPrinciple());
        team.setTeamFoundation(request.getTeamFoundation());
        team.setTeamChampionships(request.getTeamChampionships());
        return team;
    }
}
