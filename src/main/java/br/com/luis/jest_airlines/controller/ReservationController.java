package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationResponseDTO;
import br.com.luis.jest_airlines.service.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody @Valid ReservationRequestDTO reservationRequest) {
        return new ResponseEntity<>(service.create(reservationRequest), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable("id") UUID id) {
        service.cancel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/my-reservations")
    public ResponseEntity<Set<ReservationResponseDTO>> getAllReservationsByUser() {
        return new ResponseEntity<>(service.getReservationsByUser(), HttpStatus.OK);
    }
}
