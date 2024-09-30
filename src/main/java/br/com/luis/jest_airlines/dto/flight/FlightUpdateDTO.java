package br.com.luis.jest_airlines.dto.flight;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record FlightUpdateDTO(

        @NotNull(message = "Este campo não pode ser nulo")
        LocalDateTime departure,

        @NotNull(message = "Este campo não pode ser nulo")
        LocalDateTime arrival,

        @NotBlank(message = "Este campo não pode ser vazio")
        String duration,

        @NotNull(message = "Este campo não pode ser nulo")
        Integer price
) {
}
