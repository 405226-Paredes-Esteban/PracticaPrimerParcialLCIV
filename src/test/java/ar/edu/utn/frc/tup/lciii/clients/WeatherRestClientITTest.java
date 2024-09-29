package ar.edu.utn.frc.tup.lciii.clients;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherRestClientITTest {

    @Autowired
    private WeatherRestClient weatherRestClient;

    @Test
    void getLocations() {
        ResponseEntity<Location[]> response = weatherRestClient.getLocations();
        assertNotNull(response);
        assertEquals(3, Objects.requireNonNull(response.getBody()).length);
        assertEquals(1L, response.getBody()[0].id());
    }

    @Test
    void getWindiness() {
        ResponseEntity<Wind[]> response = weatherRestClient.getWindiness();
        assertNotNull(response);
        assertEquals(9, Objects.requireNonNull(response.getBody()).length);
        assertEquals(1L, response.getBody()[0].id());
    }

    @Test
    void getTemperatures() {
        ResponseEntity<Temperature[]> response = weatherRestClient.getTemperatures();
        assertNotNull(response);
        assertEquals(9, Objects.requireNonNull(response.getBody()).length);
        assertEquals(1L, response.getBody()[0].id());
    }

    @Test
    void getAirQuality() {
        ResponseEntity<AirQuality[]> response = weatherRestClient.getAirQuality();
        assertNotNull(response);
        assertEquals(9, Objects.requireNonNull(response.getBody()).length);
        assertEquals(1L, response.getBody()[0].id());
    }

    @Test
    void getCloudiness() {
        ResponseEntity<Cloudiness[]> response = weatherRestClient.getCloudiness();
        assertNotNull(response);
        assertEquals(9, Objects.requireNonNull(response.getBody()).length);
        assertEquals(1L, response.getBody()[0].id());
    }
}