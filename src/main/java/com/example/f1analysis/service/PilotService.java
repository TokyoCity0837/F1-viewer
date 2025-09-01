package com.example.f1analysis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.f1analysis.dto.PilotRequest;
import com.example.f1analysis.model.Pilot;
import com.example.f1analysis.model.Team;
import com.example.f1analysis.repository.PilotRepository;
import com.example.f1analysis.repository.TeamRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PilotService {

    private final PilotRepository PilotRepository;
    private final TeamRepository TeamRepository;

    public PilotService(PilotRepository pilotRepository, TeamRepository teamRepository) {
        this.PilotRepository = pilotRepository;
        this.TeamRepository = teamRepository;
    }

    public List<Pilot> getAllPilots() {
        return PilotRepository.findAll();
    }

    public Pilot getPilotById(Integer id) {
        return PilotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пилот с ID " + id + " не найден"));
    }

    public Pilot savePilot(PilotRequest request) {
        Pilot pilot = mapToEntity(new Pilot(), request);
        return PilotRepository.save(pilot);
    }

    public Pilot updatePilot(Integer id, PilotRequest request) {
        Pilot existingPilot = getPilotById(id);
        Pilot updatedPilot = mapToEntity(existingPilot, request);
        return PilotRepository.save(updatedPilot);
    }

    public void deletePilotById(Integer id) {
        if (!PilotRepository.existsById(id)) {
            throw new EntityNotFoundException("Пилот с ID " + id + " не найден");
        }
        PilotRepository.deleteById(id);
    }

    private Pilot mapToEntity(Pilot pilot, PilotRequest request) {
        Team team = TeamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Команда с ID " + request.getTeamId() + " не найдена"));

        pilot.setFirstName(request.getFirstName());
        pilot.setLastName(request.getLastName());
        pilot.setNationality(request.getCountry());
        pilot.setBirthDate(request.getBirthDate());
        pilot.setNumber(request.getCarNumber());
        pilot.setTeam(team);

        return pilot;
    }
}
