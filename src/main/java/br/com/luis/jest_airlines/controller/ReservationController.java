package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationResponseDTO;
import br.com.luis.jest_airlines.service.ReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode="201", description="Reservation created successfully"),
            @ApiResponse(responseCode="400", description="Invalid input data"),
            @ApiResponse(responseCode="404", description = "One or more entities not found"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @PostMapping
    @Transactional
    public ResponseEntity<ReservationResponseDTO> create(@RequestBody @Valid ReservationRequestDTO reservationRequest) {
        return new ResponseEntity<>(service.create(reservationRequest), HttpStatus.CREATED);
    }

    @Operation(summary = "Cancel reservation")
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description = "Cancel reservation successfully"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(@PathVariable("id") UUID id) {
        service.cancel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all reservations by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "All reservations by user listed successfully"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @GetMapping("/my-reservations")
    public ResponseEntity<Set<ReservationResponseDTO>> getAllReservationsByUser() {
        return new ResponseEntity<>(service.getReservationsByUser(), HttpStatus.OK);
    }
}
