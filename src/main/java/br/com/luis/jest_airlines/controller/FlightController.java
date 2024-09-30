package br.com.luis.jest_airlines.controller;


import br.com.luis.jest_airlines.dto.flight.FlightRequestDTO;
import br.com.luis.jest_airlines.dto.flight.FlightResponseDTO;
import br.com.luis.jest_airlines.dto.flight.FlightUpdateDTO;
import br.com.luis.jest_airlines.service.FlightService;
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

    @PostMapping
    @Transactional
    public ResponseEntity<FlightResponseDTO> create(@RequestBody @Valid FlightRequestDTO flightRequest) {
        return new ResponseEntity<>(service.create(flightRequest), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Set<FlightResponseDTO>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> findById(@PathVariable("id") UUID id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<FlightResponseDTO> update(@PathVariable("id") UUID id,
                                                    @RequestBody @Valid FlightUpdateDTO flightUpdate) {
        return new ResponseEntity<>(service.update(id, flightUpdate), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
