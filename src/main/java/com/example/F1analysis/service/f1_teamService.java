package com.example.F1analysis.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.F1analysis.repository.f1_teamRepository;
import com.example.F1analysis.dto.TeamRequest;
import com.example.F1analysis.model.f1_team;
import jakarta.persistence.EntityNotFoundException;

@Service
public class f1_teamService {

    private final f1_teamRepository teamRepository;

    public f1_teamService(f1_teamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }


    public List<f1_team> getAllTeams() {
        return teamRepository.findAll();
    }

    public f1_team saveTeam(TeamRequest request) {
        f1_team team = new f1_team();
        team.setName(request.getName());
        team.setCountry(request.getCountry());
        return teamRepository.save(team);
    }

    public f1_team getTeamById(Integer id) {
        return teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Команда с ID " + id + " не найдена"));
    }

    public f1_team updateTeam(Integer id, TeamRequest request) {
        f1_team existingTeam = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Команда с ID " + id + " не найдена"));
    
        existingTeam.setName(request.getName());
        existingTeam.setCountry(request.getCountry());
    
        return teamRepository.save(existingTeam);
    }
    

    public void deleteTeamById(Integer id) {
        if (!teamRepository.existsById(id)) {
            throw new EntityNotFoundException("Команда с ID " + id + " не найдена");
        }
        teamRepository.deleteById(id);
    }
}
