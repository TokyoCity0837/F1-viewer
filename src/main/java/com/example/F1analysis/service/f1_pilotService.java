package com.example.F1analysis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

import com.example.F1analysis.model.f1_pilot;
import com.example.F1analysis.model.f1_team;
import com.example.F1analysis.dto.PilotRequest;
import com.example.F1analysis.repository.f1_pilotRepository;
import com.example.F1analysis.repository.f1_teamRepository;

@Service
public class f1_pilotService {

    private final f1_pilotRepository pilotRepository;
    private final f1_teamRepository teamRepository;

    public f1_pilotService(f1_pilotRepository pilotRepository, f1_teamRepository teamRepository) {
        this.pilotRepository = pilotRepository;
        this.teamRepository = teamRepository;
    }

    public List<f1_pilot> getAllPilots() {
        return pilotRepository.findAll();
    }

    public f1_pilot getPilotById(Integer id) {
        return pilotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пилот с ID " + id + " не найден"));
    }

    public f1_pilot savePilot(PilotRequest request) {
        f1_pilot pilot = mapToEntity(new f1_pilot(), request);
        return pilotRepository.save(pilot);
    }

    public f1_pilot updatePilot(Integer id, PilotRequest request) {
        f1_pilot existingPilot = getPilotById(id);
        f1_pilot updatedPilot = mapToEntity(existingPilot, request);
        return pilotRepository.save(updatedPilot);
    }

    public void deletePilotById(Integer id) {
        if (!pilotRepository.existsById(id)) {
            throw new EntityNotFoundException("Пилот с ID " + id + " не найден");
        }
        pilotRepository.deleteById(id);
    }

    private f1_pilot mapToEntity(f1_pilot pilot, PilotRequest request) {
        f1_team team = teamRepository.findById(request.getTeamId())
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
