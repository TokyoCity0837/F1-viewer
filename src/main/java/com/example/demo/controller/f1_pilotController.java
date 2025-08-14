package com.example.demo.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.f1_pilot;
import com.example.demo.service.f1_pilotService;
import com.example.demo.DriverRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pilots")
public class f1_pilotController {

    private final f1_pilotService pilotService;

    public f1_pilotController(f1_pilotService pilotService) {
        this.pilotService = pilotService;
    }

    @PutMapping("/{id}")
    public f1_pilot updatePilot(@PathVariable Long id, @Valid @RequestBody DriverRequest request) {
    return pilotService.updatePilot(id, request);
}


    @GetMapping
    public List<f1_pilot> getAllPilots() {
        return pilotService.getAllPilots();
    }

    @PostMapping
    public f1_pilot createPilot(@Valid @RequestBody DriverRequest request) {
        return pilotService.savePilot(request);
    }

    @GetMapping("/{id}")
    public f1_pilot getPilotById(@PathVariable Long id) {
        return pilotService.getPilotById(id);
    }

    @DeleteMapping("/{id}")
    public void deletePilotById(@PathVariable Long id) {
        pilotService.deletePilotById(id);
    }
}
