package com.example.F1analysis.controller;

import com.example.F1analysis.dto.PilotRequest;
import com.example.F1analysis.model.f1_pilot;
import com.example.F1analysis.service.f1_pilotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class f1_pilotControllerTest {

    @Mock
    private f1_pilotService pilotService; 

    @InjectMocks
    private f1_pilotController controller; 

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); 
    }

    @Test
    void testGetAllPilots() {
        f1_pilot pilot1 = new f1_pilot();
        f1_pilot pilot2 = new f1_pilot();
        List<f1_pilot> mockList = Arrays.asList(pilot1, pilot2);

        when(pilotService.getAllPilots()).thenReturn(mockList);

        List<f1_pilot> result = controller.getAllPilots();

        assertThat(result).hasSize(2);
        verify(pilotService, times(1)).getAllPilots();
    }

    @Test
    void testGetPilotById() {
        f1_pilot pilot = new f1_pilot();
        when(pilotService.getPilotById(1L)).thenReturn(pilot);

        f1_pilot result = controller.getPilotById(1L);

        assertThat(result).isNotNull();
        verify(pilotService, times(1)).getPilotById(1L);
    }

    @Test
    void testCreatePilot() {
        PilotRequest request = new PilotRequest();
        f1_pilot pilot = new f1_pilot();
        when(pilotService.savePilot(request)).thenReturn(pilot);

        f1_pilot result = controller.createPilot(request);

        assertThat(result).isNotNull();
        verify(pilotService, times(1)).savePilot(request);
    }

    @Test
    void testUpdatePilot() {
        PilotRequest request = new PilotRequest();
        f1_pilot pilot = new f1_pilot();
        when(pilotService.updatePilot(1L, request)).thenReturn(pilot);

        f1_pilot result = controller.updatePilot(1L, request);

        assertThat(result).isNotNull();
        verify(pilotService, times(1)).updatePilot(1L, request);
    }

    @Test
    void testDeletePilotById() {
        doNothing().when(pilotService).deletePilotById(1L);

        controller.deletePilotById(1L);

        verify(pilotService, times(1)).deletePilotById(1L);
    }
}
