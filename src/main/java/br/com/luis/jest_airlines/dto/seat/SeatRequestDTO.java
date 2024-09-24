package br.com.luis.jest_airlines.dto.seat;

public record SeatRequestDTO(
        String number,

        String type,

        String status
) {
}
