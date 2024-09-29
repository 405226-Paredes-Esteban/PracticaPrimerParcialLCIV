package ar.edu.utn.frc.tup.lciii.clients;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class WeatherRestClientTest {
    @MockBean
    private RestTemplate restTemplate;

    private final String url = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/";
    //Lo levantamos como Spy para poder usar los metodos como estan definidos
    @SpyBean
    private WeatherRestClient weatherRestClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLocations() {
        Location location1 = new Location(1L, "Test1",20.0F,20.0F);
        Location location2 = new Location(2L, "Test2",30.0F,30.0F);
        Location[] arrLocation = new Location[]{location1, location2};
        when(restTemplate.getForEntity(url+"location",Location[].class))
                .thenReturn(ResponseEntity.ok(arrLocation));
        ResponseEntity<Location[]> result = weatherRestClient.getLocations();
        assertNotNull(result);
        assertEquals(arrLocation.length, Objects.requireNonNull(result.getBody()).length);
        assertEquals(arrLocation[0], result.getBody()[0]);
        assertEquals(arrLocation[1].id(), result.getBody()[1].id());
    }

    @Test
    void getWindiness() {
        Wind wind1 = new Wind(1L,1L,20,30, LocalDateTime.MIN);
        Wind wind2 = new Wind(2L,1L,20,30, LocalDateTime.MAX);
        Wind[] arrWind = new Wind[]{wind1, wind2};
        when(restTemplate.getForEntity(url+"wind",Wind[].class))
            .thenReturn(ResponseEntity.ok(arrWind));
        ResponseEntity<Wind[]> result = weatherRestClient.getWindiness();
        assertNotNull(result);
        assertEquals(arrWind.length, Objects.requireNonNull(result.getBody()).length);
        assertEquals(arrWind[0], result.getBody()[0]);
        assertEquals(arrWind[1].id(), result.getBody()[1].id());
    }

    @Test
    void getTemperatures() {
        Temperature temp1 = new Temperature(1L, 1L, 20,'C',LocalDateTime.MIN);
        Temperature temp2 = new Temperature(2L, 1L, 20,'C',LocalDateTime.MAX);
        Temperature[] arrTemp = new Temperature[]{temp1, temp2};
        when(restTemplate.getForEntity(url+"temperature",Temperature[].class))
                .thenReturn(ResponseEntity.ok(arrTemp));
        ResponseEntity<Temperature[]> result = weatherRestClient.getTemperatures();
        assertNotNull(result);
        assertEquals(arrTemp.length, Objects.requireNonNull(result.getBody()).length);
        assertEquals(arrTemp[0], result.getBody()[0]);
        assertEquals(arrTemp[1].id(), result.getBody()[1].id());
    }

    @Test
    void getAirQuality() {
        AirQuality quality1 = new AirQuality(1L,1L,0,LocalDateTime.MIN);
        AirQuality quality2 = new AirQuality(2L,1L,5, LocalDateTime.MAX);
        AirQuality[] arrQuality = new AirQuality[]{quality1, quality2};
        when(restTemplate.getForEntity(url+"air_quality",AirQuality[].class))
            .thenReturn(ResponseEntity.ok(arrQuality));
        ResponseEntity<AirQuality[]> result = weatherRestClient.getAirQuality();
        assertNotNull(result);
        assertEquals(arrQuality.length, Objects.requireNonNull(result.getBody()).length);
        assertEquals(arrQuality[0], result.getBody()[0]);
        assertEquals(arrQuality[1].id(), result.getBody()[1].id());
    }

    @Test
    void getCloudiness() {
        Cloudiness cloud1 = new Cloudiness(1L,1L,20,LocalDateTime.MIN);
        Cloudiness cloud2 = new Cloudiness(2L,1L,20,LocalDateTime.MAX);
        Cloudiness[] arrCloud = new Cloudiness[]{cloud1, cloud2};
        when(restTemplate.getForEntity(url+"cloudiness",Cloudiness[].class))
                .thenReturn(ResponseEntity.ok(arrCloud));
        ResponseEntity<Cloudiness[]> result = weatherRestClient.getCloudiness();
        assertNotNull(result);
        assertEquals(arrCloud.length, Objects.requireNonNull(result.getBody()).length);
        assertEquals(arrCloud[0], result.getBody()[0]);
        assertEquals(arrCloud[1].id(), result.getBody()[1].id());
    }

}