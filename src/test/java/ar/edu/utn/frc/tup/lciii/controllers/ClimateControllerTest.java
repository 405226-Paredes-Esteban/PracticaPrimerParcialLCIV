package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.clients.Location;
import ar.edu.utn.frc.tup.lciii.dtos.common.WeatherDto;
import ar.edu.utn.frc.tup.lciii.services.ClimateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ClimateControllerTest {

    @InjectMocks
    private ClimateController climateController;

    @Mock
    private ClimateService climateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLocations() {
        Location[] mockLocations = new Location[]{ /* create mock locations */ };
        when(climateService.getLocations()).thenReturn(mockLocations);

        ResponseEntity<Location[]> response = climateController.getLocations();

        assertEquals(200, response.getStatusCodeValue());
        assertArrayEquals(mockLocations, response.getBody());
        verify(climateService).getLocations();
    }

    @Test
    void testGetLocationFound() {
        Long id = 1L;
        LocalDateTime dateTime = LocalDateTime.now();
        WeatherDto mockWeatherDto = new WeatherDto(); // Populate with test data

        when(climateService.getLocation(id, dateTime)).thenReturn(mockWeatherDto);

        ResponseEntity<WeatherDto> response = climateController.getLocation(id, dateTime);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockWeatherDto, response.getBody());
        verify(climateService).getLocation(id, dateTime);
    }

    @Test
    void testGetLocationNotFound() {
        Long id = 1L;
        LocalDateTime dateTime = LocalDateTime.now();

        when(climateService.getLocation(id, dateTime)).thenReturn(null);

        ResponseEntity<WeatherDto> response = climateController.getLocation(id, dateTime);

        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
        verify(climateService).getLocation(id, dateTime);
    }
}

