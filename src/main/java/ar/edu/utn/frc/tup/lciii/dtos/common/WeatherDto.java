package ar.edu.utn.frc.tup.lciii.dtos.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {
    private LocationDto location;
    private TemperatureDto temperature;
    private String wind;
    private AirQualityDto airQuality;
    private CloudinessDto cloudiness;
}
