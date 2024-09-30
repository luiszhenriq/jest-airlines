package br.com.luis.jest_airlines.dto.seat;

import jakarta.validation.constraints.NotBlank;

public record SeatRequestDTO(

        @NotBlank(message = "Este campo não pode ser vazio")
        String number,

        @NotBlank(message = "Este campo não pode ser vazio")
        String type,

        @NotBlank(message = "Este campo não pode ser vazio")
        String status
) {
}
