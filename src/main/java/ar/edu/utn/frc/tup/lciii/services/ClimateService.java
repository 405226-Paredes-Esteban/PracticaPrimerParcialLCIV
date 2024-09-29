package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.clients.*;
import ar.edu.utn.frc.tup.lciii.dtos.common.*;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class ClimateService {
     @Autowired
    WeatherRestClient weatherRestClient;
    @Qualifier("modelMapper")
    @Autowired
    private ModelMapper modelMapper;

    public Location[] getLocations(){
         ResponseEntity<Location[]> locations= weatherRestClient.getLocations();
         if(!locations.getStatusCode().is2xxSuccessful()){
             return null;
         }
         return locations.getBody();
    };

    public WeatherDto getLocation(Long id, LocalDateTime date){
        WeatherDto weatherDto = new WeatherDto();
        LocationDto auxLoc = getLocationById(id);
        if(auxLoc == null){
            return null;
        }
        weatherDto.setLocation(auxLoc);
        TemperatureDto auxTemp = getTemperatureByIdAndDate(id, date);
        if(auxTemp == null){
            return null;
        }
        weatherDto.setTemperature(auxTemp);
        CloudinessDto cloudinessDto = getCloudinessByIdAndDate(id, date);
        if(cloudinessDto == null){
            return null;
        }
        weatherDto.setCloudiness(cloudinessDto);
        AirQualityDto airQualityDto = getAirQualityByIdAndDate(id, date);
        if(airQualityDto == null){
            return null;
        }
        weatherDto.setAirQuality(airQualityDto);
        weatherDto.setWind(getWindByIdAndDate(id, date));
        return weatherDto;
    };

    private LocationDto getLocationById(Long id){
        ResponseEntity<Location[]> locations = weatherRestClient.getLocations();
        if(!locations.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        for(Location location : Objects.requireNonNull(locations.getBody())){
            if(location.id().equals(id)){
                return new LocationDto(location.id(), location.name());
            }
        }
        return null;
    };

     private TemperatureDto getTemperatureByIdAndDate(Long id, LocalDateTime date){
        ResponseEntity<Temperature[]> temperatures = weatherRestClient.getTemperatures();
        if(!temperatures.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        for(Temperature temperature : Objects.requireNonNull(temperatures.getBody())){
            if(temperature.id().equals(id)&&temperature.created_at().toLocalDate().isEqual(date.toLocalDate())){
                return new TemperatureDto(temperature.temperature(),temperature.unit());
            }
        }
        return null;
     };

     private CloudinessDto getCloudinessByIdAndDate(Long id, LocalDateTime date){
         ResponseEntity<Cloudiness[]> cloudiness = weatherRestClient.getCloudiness();
         if(!cloudiness.getStatusCode().is2xxSuccessful()) {
             return null;
         }
         for(Cloudiness cloud: Objects.requireNonNull(cloudiness.getBody())){
             if(cloud.id().equals(id)&&cloud.created_at().toLocalDate().isEqual(date.toLocalDate())){
                 return getCloudiness(cloud);
             }
         }
         return null;
     };

     private AirQualityDto getAirQualityByIdAndDate(Long id, LocalDateTime date){
        ResponseEntity<AirQuality[]> airQuality = weatherRestClient.getAirQuality();
        if(!airQuality.getStatusCode().is2xxSuccessful()) {
            return null;
        }
        for(AirQuality air: Objects.requireNonNull(airQuality.getBody())){
            if(air.id().equals(id)&&air.created_at().toLocalDate().isEqual(date.toLocalDate())){
                return getAirQuality(air);
            }
        }
        return null;
    }


     private CloudinessDto getCloudiness(Cloudiness cloud){
         CloudinessDto aux = new CloudinessDto();
         aux.setIndex(cloud.cloudiness());
         if(cloud.cloudiness()==0){
            aux.setDescription("Clear Sky");
         }
         else if(cloud.cloudiness()>=1&&cloud.cloudiness()<=3){
             aux.setDescription("Few Clouds");
         }
         else if(cloud.cloudiness()>=4&&cloud.cloudiness()<=6){
             aux.setDescription("Sky half cludy");
         }
         else{
             aux.setDescription("Sky completely cludy");
         }
         return aux;
     }

     private AirQualityDto getAirQuality(AirQuality airQuality){
         AirQualityDto aux = new AirQualityDto();
         aux.setIndex(airQuality.quality());
         if(airQuality.quality()>=0&&airQuality.quality()<=50){
             aux.setDescription("Good");
         }
         else if(airQuality.quality()>=51&&airQuality.quality()<=100){
             aux.setDescription("Moderate");
         }
         else if(airQuality.quality()>=101&&airQuality.quality()<=150){
             aux.setDescription("Unhealthy for Sensitive Groups");
         }
         else if(airQuality.quality()>=151&&airQuality.quality()<=200){
             aux.setDescription("Unhealthy");
         }
         else if(airQuality.quality()>=201&&airQuality.quality()<=300){
            aux.setDescription("Very Unhealthy");
         }
         else{
             aux.setDescription("Hazardous");
         }
         return aux;
     }

     private String getWindByIdAndDate(Long id, LocalDateTime date){
         ResponseEntity<Wind[]> winds = weatherRestClient.getWindiness();
         if(!winds.getStatusCode().is2xxSuccessful()) {
             return "Couldn't obtain wind information";
         }
         for(Wind wind: Objects.requireNonNull(winds.getBody())){
             if(wind.id().equals(id)&&wind.created_at().toLocalDate().isEqual(date.toLocalDate())){
                 return wind.speed()+" Km/h from "+ getDirection(wind.direction());
             }
         }
         return "Couldn't obtain wind information";
     }

     private String getDirection(int direction){
         if(direction>=0&&direction<=45||direction>=325&&direction<=360){
             return "North";
         }
         else if(direction>=46&&direction<=145){
             return "East";
         }
         else if(direction>=135&&direction<=225){
             return "South";
         }
         else if(direction>=225&&direction<=324){
             return "West";
         }
         return "Unknown";
     }
}
