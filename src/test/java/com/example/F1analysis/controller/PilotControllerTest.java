package com.example.F1analysis.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.f1analysis.controller.PilotController;
import com.example.f1analysis.dto.PilotRequest;
import com.example.f1analysis.model.Pilot;
import com.example.f1analysis.service.PilotService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class PilotControllerTest {

    @Mock
    private PilotService pilotService;

    @InjectMocks
    private PilotController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPilots() {
        Pilot pilot1 = new Pilot();
        Pilot pilot2 = new Pilot();
        List<Pilot> mockList = Arrays.asList(pilot1, pilot2);
        when(pilotService.getAllPilots()).thenReturn(mockList);
        List<Pilot> result = controller.getAllPilots();
        assertThat(result).hasSize(2);
        verify(pilotService, times(1)).getAllPilots();
    }

    @Test
    void testGetPilotById() {
        Pilot pilot = new Pilot();
        when(pilotService.getPilotById(1)).thenReturn(pilot);
        Pilot result = controller.getPilotById(1);
        assertThat(result).isNotNull();
        verify(pilotService, times(1)).getPilotById(1);
    }

    @Test
    void testCreatePilot() {
        PilotRequest request = new PilotRequest();
        Pilot pilot = new Pilot();
        when(pilotService.savePilot(request)).thenReturn(pilot);
        Pilot result = controller.createPilot(request);
        assertThat(result).isNotNull();
        verify(pilotService, times(1)).savePilot(request);
    }

    @Test
    void testUpdatePilot() {
        PilotRequest request = new PilotRequest();
        Pilot pilot = new Pilot();
        when(pilotService.updatePilot(1, request)).thenReturn(pilot);
        Pilot result = controller.updatePilot(1, request);
        assertThat(result).isNotNull();
        verify(pilotService, times(1)).updatePilot(1, request);
    }

    @Test
    void testDeletePilotById() {
        doNothing().when(pilotService).deletePilotById(1);
        controller.deletePilotById(1);
        verify(pilotService, times(1)).deletePilotById(1);
    }

    @Test
    void testFetchingPilotCreate() {
        PilotRequest request = new PilotRequest();
        Pilot pilot = new Pilot();
        when(pilotService.savePilot(request)).thenReturn(pilot);
        Pilot created = controller.createPilot(request);
        when(pilotService.getPilotById(1)).thenReturn(created);
        Pilot fetched = controller.getPilotById(1);
        assertThat(fetched).isEqualTo(created);
        verify(pilotService, times(1)).savePilot(request);
        verify(pilotService, times(1)).getPilotById(1);
    }

    @Test
    void testFetchingPilotUpdate() {
        PilotRequest request = new PilotRequest();
        Pilot updatedPilot = new Pilot();
        when(pilotService.updatePilot(1, request)).thenReturn(updatedPilot);
        Pilot result = controller.updatePilot(1, request);
        when(pilotService.getPilotById(1)).thenReturn(updatedPilot);
        Pilot fetched = controller.getPilotById(1);
        assertThat(result).isEqualTo(fetched);
        verify(pilotService, times(1)).updatePilot(1, request);
        verify(pilotService, times(1)).getPilotById(1);
    }

    @Test
    void testVerefiedPilotDeleting() {
        doNothing().when(pilotService).deletePilotById(1);
        controller.deletePilotById(1);
        when(pilotService.getAllPilots()).thenReturn(Arrays.asList());
        List<Pilot> pilots = controller.getAllPilots();
        assertThat(pilots).isEmpty();
        verify(pilotService, times(1)).deletePilotById(1);
        verify(pilotService, times(1)).getAllPilots();
    }
}
