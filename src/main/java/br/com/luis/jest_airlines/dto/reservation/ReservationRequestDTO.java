package br.com.luis.jest_airlines.dto.reservation;


import br.com.luis.jest_airlines.model.enums.PaymentMethod;
import br.com.luis.jest_airlines.model.enums.ReservationStatus;

import java.util.Set;
import java.util.UUID;

public record ReservationRequestDTO(

        UUID userId,

        UUID flightId,

        Set<UUID> seatsId,

        ReservationStatus status,

        PaymentMethod paymentMethod
) {
}
