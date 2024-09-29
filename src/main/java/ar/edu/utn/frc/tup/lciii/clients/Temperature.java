package ar.edu.utn.frc.tup.lciii.clients;

import java.time.LocalDateTime;

public record Temperature(Long id, Long location_id, int temperature, char unit, LocalDateTime created_at) {
    /**
     *     "id": 1,
     *     "location_id": 1,
     *     "temperature": 30,
     *     "unit": "C",
     *     "created_at": "2017-01-01T00:00:00.000Z"
     */
}
