package br.com.luis.jest_airlines.dto.flight;

import java.time.LocalDateTime;

public record FlightRequestDTO(String number,

                               String origin,

                               String destiny,

                               LocalDateTime departure,

                               LocalDateTime arrival,

                               String duration,

                               Integer price) {
}
