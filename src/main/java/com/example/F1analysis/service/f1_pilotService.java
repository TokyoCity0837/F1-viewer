package com.example.F1analysis.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.F1analysis.repository.f1_pilotRepository;
import com.example.F1analysis.dto.DriverRequest;
import com.example.F1analysis.model.f1_pilot;
import com.example.F1analysis.model.f1_team;
import com.example.F1analysis.repository.f1_teamRepository;
import jakarta.persistence.EntityNotFoundException;

import java.sql.Date;

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


    public f1_pilot savePilot(DriverRequest request) {
        f1_team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Команда с ID " + request.getTeamId() + " не найдена"));

        f1_pilot pilot = new f1_pilot(
                request.getFirstName(),
                request.getLastName(),
                request.getCountry(),
                Date.valueOf(request.getBirthDate()),
                request.getCarNumber(),
                team
        );

        return pilotRepository.save(pilot);
    }

    public f1_pilot updatePilot(Long id, DriverRequest request) {
        f1_pilot existingPilot = pilotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пилот с ID " + id + " не найден"));
    
        f1_team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new EntityNotFoundException("Команда с ID " + request.getTeamId() + " не найдена"));
    
        existingPilot.setFirstName(request.getFirstName());
        existingPilot.setLastName(request.getLastName());
        existingPilot.setNationality(request.getCountry());
        existingPilot.setBirthDate(Date.valueOf(request.getBirthDate()));
        existingPilot.setNumber(request.getCarNumber());
        existingPilot.setTeam(team);
    
        return pilotRepository.save(existingPilot);
    }
    
    public f1_pilot getPilotById(Long id) {
        return pilotRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Пилот с ID " + id + " не найден"));
    }

    public void deletePilotById(Long id) {
        if (!pilotRepository.existsById(id)) {
            throw new EntityNotFoundException("Пилот с ID " + id + " не найден");
        }
        pilotRepository.deleteById(id);
    }
}
