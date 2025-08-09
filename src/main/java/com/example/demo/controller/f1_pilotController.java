package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.f1_pilot;
import com.example.demo.service.f1_pilotService;

@RestController
@RequestMapping("/pilots")
public class f1_pilotController {

    private final f1_pilotService pilotService;

    public f1_pilotController(f1_pilotService pilotService) {
        this.pilotService = pilotService;
    }

    @GetMapping
    public List<f1_pilot> getAllPilots() {
        return pilotService.getAllPilots();
    }

    @PostMapping
    public f1_pilot createPilot(@RequestBody f1_pilot pilot) {
        return pilotService.savePilot(pilot);
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