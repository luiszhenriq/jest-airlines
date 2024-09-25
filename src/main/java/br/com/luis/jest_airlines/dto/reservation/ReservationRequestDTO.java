package br.com.luis.jest_airlines.dto.reservation;


import java.util.Set;
import java.util.UUID;

public record ReservationRequestDTO(

        UUID userId,

        UUID flightId,

        Set<UUID> seatsId,

        String status,

        String paymentMethod
) {
}
