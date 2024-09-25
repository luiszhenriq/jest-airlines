package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationResponseDTO;
import br.com.luis.jest_airlines.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService service;

    @PostMapping
    @Transactional
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody ReservationRequestDTO reservationRequest) {
        return new ResponseEntity<>(service.create(reservationRequest), HttpStatus.CREATED);
    }
}
