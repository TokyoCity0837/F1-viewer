package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.repository.f1_pilotRepository;
import com.example.demo.model.f1_pilot;

@Service
public class f1_pilotService {

    private final f1_pilotRepository pilotRepository;

    public f1_pilotService(f1_pilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    public List<f1_pilot> getAllPilots() {
        return pilotRepository.findAll();
    }

    public f1_pilot savePilot(f1_pilot pilot) {
        return pilotRepository.save(pilot);
    }

    public f1_pilot getPilotById(Integer id) {
        return pilotRepository.findById(id).orElse(null);
    }

    public void deletePilotById(Integer id) {
        pilotRepository.deleteById(id);
    }
}
