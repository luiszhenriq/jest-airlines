package br.com.luis.jest_airlines.dto.seat;

import java.util.UUID;

public record SeatResponseDTO(
        UUID id,

        String number,

        String type,

        String status
) {
}
