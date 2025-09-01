package com.example.f1analysis.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.f1analysis.dto.PilotRequest;
import com.example.f1analysis.model.Pilot;
import com.example.f1analysis.service.PilotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pilots")
public class PilotController {

    private final PilotService pilotService;

    public PilotController(PilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping
    public List<Pilot> getAllPilots() {
        return pilotService.getAllPilots();
    }

    @GetMapping("/{id}")
    public Pilot getPilotById(@PathVariable Integer id) {
        return pilotService.getPilotById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Pilot createPilot(@Valid @RequestBody PilotRequest request) {
        return pilotService.savePilot(request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Pilot updatePilot(@PathVariable Integer id, @Valid @RequestBody PilotRequest request) {
        return pilotService.updatePilot(id, request);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deletePilotById(@PathVariable Integer id) {
        pilotService.deletePilotById(id);
    }
}
