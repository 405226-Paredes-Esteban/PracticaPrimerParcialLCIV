package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.clients.Location;
import ar.edu.utn.frc.tup.lciii.clients.WeatherRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ClimateServiceTest {
    @Mock
    private WeatherRestClient weatherRestClient;

    @InjectMocks
    private ClimateService climateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getLocations_Success() {
        Location[] mockLocations = new Location[]{new Location(1L,"Test1",20.0F,20.0F), new Location(2L,"Test2",30.0F,30.0F)};
        ResponseEntity<Location[]> responseEntity = ResponseEntity.ok(mockLocations);
        when(weatherRestClient.getLocations()).thenReturn(responseEntity);

        Location[] locations = climateService.getLocations();
        assertNotNull(locations);
        assertEquals(2, locations.length);
    }

    @Test
    void getLocations_Failure() {
        ResponseEntity<Location[]> responseEntity = ResponseEntity.badRequest().build();
        when(weatherRestClient.getLocations()).thenReturn(responseEntity);
        Location[] locations = climateService.getLocations();
        assertNull(locations);
    }

    @Test
    void getDirectionTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getDirection(); //Declaro el metodo dentro del test
        //Invoco con el args usando lo que necesito
        String result = (String) method.invoke(new ClimateService(), 0);
        assertEquals("North", result);
    }

    private Method getDirection() throws NoSuchMethodException {
        //Declaramos el metodo y le decimos que tipo de dato de param recibe
        Method method = ClimateService.class.getDeclaredMethod("getDirection", int.class);
        method.setAccessible(true);
        return method;
    }

}