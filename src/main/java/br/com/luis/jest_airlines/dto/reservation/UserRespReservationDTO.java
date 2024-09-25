package br.com.luis.jest_airlines.dto.reservation;

import java.util.UUID;

public record UserRespReservationDTO(
        UUID id,

        String fullName
) {
}
