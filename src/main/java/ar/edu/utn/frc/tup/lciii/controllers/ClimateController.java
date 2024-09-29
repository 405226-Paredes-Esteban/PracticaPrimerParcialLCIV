package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.clients.Location;
import ar.edu.utn.frc.tup.lciii.dtos.common.WeatherDto;
import ar.edu.utn.frc.tup.lciii.services.ClimateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ClimateController {
    @Autowired
    private ClimateService climateService;

    @GetMapping("/weather/locations")
    public ResponseEntity<Location[]> getLocations() {
        return ResponseEntity.status(200).body(climateService.getLocations());
    }

    @GetMapping("/weather/location/{id}")
    public ResponseEntity<WeatherDto> getLocation(@PathVariable("id") Long id, @RequestParam("datetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {
        WeatherDto auxDto = climateService.getLocation(id, dateTime);
        if (auxDto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(auxDto);
    }

}
