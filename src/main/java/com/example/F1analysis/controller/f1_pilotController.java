package com.example.F1analysis.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.example.F1analysis.dto.PilotRequest;
import com.example.F1analysis.model.f1_pilot;
import com.example.F1analysis.service.f1_pilotService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pilots")
public class f1_pilotController {

    private final f1_pilotService pilotService;

    public f1_pilotController(f1_pilotService pilotService) {
        this.pilotService = pilotService;
    }

    @PutMapping("/{id}")
    public f1_pilot updatePilot(@PathVariable Integer id, @Valid @RequestBody PilotRequest request) {
    return pilotService.updatePilot(id, request);
}


    @GetMapping
    public List<f1_pilot> getAllPilots() {
        return pilotService.getAllPilots();
    }

    @PostMapping
    public f1_pilot createPilot(@Valid @RequestBody PilotRequest request) {
        return pilotService.savePilot(request);
    }

    @GetMapping("/{id}")
    public f1_pilot getPilotById(@PathVariable Integer id) {
        return pilotService.getPilotById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePilotById(@PathVariable Integer id) {
        pilotService.deletePilotById(id);
    }
}
