package br.com.luis.jest_airlines.dto.reservation;


import br.com.luis.jest_airlines.model.enums.PaymentMethod;
import br.com.luis.jest_airlines.model.enums.ReservationStatus;
import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public record ReservationRequestDTO(

        @NotNull(message = "Este campo não pode ser nulo")
        UUID userId,

        @NotNull(message = "Este campo não pode ser nulo")
        UUID flightId,

        @NotNull(message = "Este campo não pode ser nulo")
        Set<UUID> seatsId,

        ReservationStatus status,

        PaymentMethod paymentMethod
) {
}
