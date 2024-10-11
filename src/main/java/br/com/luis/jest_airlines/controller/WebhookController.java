package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.webhook.Body;
import br.com.luis.jest_airlines.infra.exception.IdNotFoundException;
import br.com.luis.jest_airlines.model.Reservation;
import br.com.luis.jest_airlines.model.Seat;
import br.com.luis.jest_airlines.model.enums.ReservationStatus;
import br.com.luis.jest_airlines.repositories.ReservationRepository;
import br.com.luis.jest_airlines.repositories.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
public class WebhookController {

    private final ReservationRepository reservationRepository;
    private final SeatRepository seatRepository;

    @PostMapping
    public ResponseEntity<String> webhook(@RequestBody Body body) {

        Reservation reservation = reservationRepository.findById(body.data().object().metadata().reservation_id())
                .orElseThrow();

        reservation.getReservedSeats().forEach(seatId -> {
           Seat seat = seatRepository.findById(seatId.getId())
                    .orElseThrow(() -> new IdNotFoundException("Id não foi encontrado"));
            seat.setStatus("Reservado");
        });

        reservation.setStatus(ReservationStatus.CONFIRMADO);

        reservationRepository.save(reservation);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
