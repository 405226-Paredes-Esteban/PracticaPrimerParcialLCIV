package ar.edu.utn.frc.tup.lciii.clients;

import java.time.LocalDateTime;

public record AirQuality(Long id, Long location_id, int quality, LocalDateTime created_at) {
    /**
     *     "id": 1,
     *     "location_id": 1,
     *     "quality": 50,
     *     "created_at": "2017-01-01T00:00:00.000Z"
     */
}
