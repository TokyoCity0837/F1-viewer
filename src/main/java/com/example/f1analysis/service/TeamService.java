package com.example.f1analysis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.f1analysis.dto.TeamRequest;
import com.example.f1analysis.model.Team;
import com.example.f1analysis.repository.TeamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class TeamService {

    private final TeamRepository TeamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.TeamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return TeamRepository.findAll();
    }

    public Team getTeamById(Integer id) {
        return TeamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Команда с ID " + id + " не найдена"));
    }

    public Team saveTeam(TeamRequest request) {
        Team team = mapToEntity(new Team(), request);
        return TeamRepository.save(team);
    }

    public Team updateTeam(Integer id, TeamRequest request) {
        Team existingTeam = getTeamById(id);
        Team updatedTeam = mapToEntity(existingTeam, request);
        return TeamRepository.save(updatedTeam);
    }

    public void deleteTeamById(Integer id) {
        if (!TeamRepository.existsById(id)) {
            throw new EntityNotFoundException("Команда с ID " + id + " не найдена");
        }
        TeamRepository.deleteById(id);
    }

    private Team mapToEntity(Team team, TeamRequest request) {
        team.setTeamName(request.getTeamName());
        team.setCountry(request.getBaseCountry());
        team.setTeamPrinciple(request.getTeamPrinciple());
        team.setTeamFoundation(request.getTeamFoundation());
        team.setTeamChampionships(request.getTeamChampionships());
        return team;
    }
}
