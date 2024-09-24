package br.com.luis.jest_airlines.dto.flight;

import br.com.luis.jest_airlines.dto.seat.SeatResponseDTO;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record FlightResponseDTO(
        UUID id,

        String number,

        String origin,

        String destiny
        ,
        LocalDateTime departure,

        LocalDateTime arrival
        ,
        String duration,

        Integer price,

        Set<SeatResponseDTO> availableSeats
) {
}
