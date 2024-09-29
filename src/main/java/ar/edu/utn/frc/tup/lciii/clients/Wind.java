package ar.edu.utn.frc.tup.lciii.clients;

import java.time.LocalDateTime;

public record Wind(Long id, Long location_id, int speed, int direction, LocalDateTime created_at) {
}
