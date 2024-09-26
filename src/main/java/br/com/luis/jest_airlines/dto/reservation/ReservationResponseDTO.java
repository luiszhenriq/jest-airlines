package br.com.luis.jest_airlines.dto.reservation;

import br.com.luis.jest_airlines.dto.seat.SeatResponseDTO;
import br.com.luis.jest_airlines.model.enums.PaymentMethod;
import br.com.luis.jest_airlines.model.enums.ReservationStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record ReservationResponseDTO(
        UUID id,

        LocalDateTime dateOfReservation,

        Integer value,

        ReservationStatus status,

        PaymentMethod paymentMethod,

        UserRespReservationDTO user,

        FlightRespReservationDTO flight,

        Set<SeatResponseDTO> reservedSeats) {
}
