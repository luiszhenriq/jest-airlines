package br.com.luis.jest_airlines.dto.flight;

import br.com.luis.jest_airlines.dto.seat.SeatRequestDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.Set;

public record FlightRequestDTO(String number,

                               @NotBlank(message = "Este campo não pode ser vazio")
                               String origin,

                               @NotBlank(message = "Este campo não pode ser vazio")
                               String destiny,

                               @NotNull(message = "Este campo não pode ser nulo")
                               LocalDateTime departure,

                               @NotNull(message = "Este campo não pode ser nulo")
                               LocalDateTime arrival,

                               @NotBlank(message = "Este campo não pode ser vazio")
                               String duration,

                               @NotNull(message = "Este campo não pode ser nulo")
                               Integer price,

                               @NotNull(message = "Este campo não pode ser nulo")
                               Set<SeatRequestDTO> availableSeats) {
}
