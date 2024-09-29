package ar.edu.utn.frc.tup.lciii.clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class WeatherRestClient {
    @Autowired
    private RestTemplate restTemplate;
    private static final String RESILLIENCE4J_NAME = "resilience4j";
    private static final String FALLBACK_METHOD = "fallback";
    private final String baseUrl = "https://my-json-server.typicode.com/LCIV-2023/fake-weather/";

    @CircuitBreaker(name = RESILLIENCE4J_NAME,fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<Location[]> getLocations(){
        return restTemplate.getForEntity(baseUrl+"location", Location[].class);
    }

    @CircuitBreaker(name = RESILLIENCE4J_NAME,fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<Wind[]> getWindiness(){
        return restTemplate.getForEntity(baseUrl+"wind", Wind[].class);
    }

    @CircuitBreaker(name = RESILLIENCE4J_NAME,fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<Temperature[]> getTemperatures(){
        return restTemplate.getForEntity(baseUrl+"temperature", Temperature[].class);
    }

    @CircuitBreaker(name = RESILLIENCE4J_NAME,fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<AirQuality[]> getAirQuality(){
        return restTemplate.getForEntity(baseUrl+"air_quality", AirQuality[].class);
    }

    @CircuitBreaker(name = RESILLIENCE4J_NAME,fallbackMethod = FALLBACK_METHOD)
    public ResponseEntity<Cloudiness[]> getCloudiness(){
        return restTemplate.getForEntity(baseUrl+"cloudiness", Cloudiness[].class);
    }

    public ResponseEntity<String> fallback(Exception ex){
        return ResponseEntity.status(503).body("Response from Circuit Breaker: "+ ex.getMessage());
    }
}
