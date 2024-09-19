package br.com.luis.jest_airlines.dto.flight;

import java.time.LocalDateTime;

public record FlightUpdateDTO(

        LocalDateTime departure,

        LocalDateTime arrival,

        String duration,

        Integer price
) {
}
