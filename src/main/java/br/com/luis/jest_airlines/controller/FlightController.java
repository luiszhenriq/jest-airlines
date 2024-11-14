package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.flight.FlightResponseDTO;
import br.com.luis.jest_airlines.dto.flight.FlightUpdateDTO;
import br.com.luis.jest_airlines.service.FlightService;
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
@RequestMapping("/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService service;


    @Operation(summary = "Get all flights")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description = "Return all flights successfully"),
            @ApiResponse(responseCode="401", description = "Unauthorized"),
    })
    @GetMapping
    public ResponseEntity<Set<FlightResponseDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get flight by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description="Flight listed successfully"),
            @ApiResponse(responseCode="404", description="Flight not found"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> findById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @Operation(summary = "Update flight")
    @ApiResponses(value = {
            @ApiResponse(responseCode="200", description="Flight updated successfully"),
            @ApiResponse(responseCode="400", description="Invalid input data"),
            @ApiResponse(responseCode="404", description="Flight not found"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FlightResponseDTO> update(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid FlightUpdateDTO flightUpdate) {
        return new ResponseEntity<>(service.update(id, flightUpdate), HttpStatus.OK);
    }

    @Operation(summary = "Delete flight by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode="204", description="Flight deleted successfully"),
            @ApiResponse(responseCode="401", description="Unauthorized"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
