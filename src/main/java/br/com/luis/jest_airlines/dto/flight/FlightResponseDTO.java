package br.com.luis.jest_airlines.dto.flight;

import java.time.LocalDateTime;
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

        Integer price
        
        ) {
}
