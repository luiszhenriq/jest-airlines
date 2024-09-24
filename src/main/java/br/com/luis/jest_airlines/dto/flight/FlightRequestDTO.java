package br.com.luis.jest_airlines.dto.flight;

import br.com.luis.jest_airlines.dto.seat.SeatRequestDTO;

import java.time.LocalDateTime;
import java.util.Set;

public record FlightRequestDTO(String number,

                               String origin,

                               String destiny,

                               LocalDateTime departure,

                               LocalDateTime arrival,

                               String duration,

                               Integer price,

                               Set<SeatRequestDTO> availableSeats) {
}
