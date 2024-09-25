package br.com.luis.jest_airlines.dto.reservation;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightRespReservationDTO(
        UUID id,

        String number,

        String origin,

        String destiny
        ,
        LocalDateTime departure,

        LocalDateTime arrival
        ,
        String duration
) {
}
