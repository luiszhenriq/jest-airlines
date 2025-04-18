package br.com.luis.jest_airlines.service;


import br.com.luis.jest_airlines.dto.flight.FlightResponseDTO;
import br.com.luis.jest_airlines.dto.flight.FlightUpdateDTO;
import br.com.luis.jest_airlines.dto.seat.SeatResponseDTO;
import br.com.luis.jest_airlines.infra.exception.IdNotFoundException;
import br.com.luis.jest_airlines.model.Flight;
import br.com.luis.jest_airlines.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository repository;



    public Set<FlightResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::flightResponseDTO)
                .collect(Collectors.toSet());
    }

    public FlightResponseDTO findById(UUID id) {

        Flight flight = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));

        return flightResponseDTO(flight);
    }

    public FlightResponseDTO update(UUID id, FlightUpdateDTO flightUpdate) {

        Flight flight = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));

        flight.setDeparture(flightUpdate.departure());
        flight.setArrival(flightUpdate.arrival());
        flight.setDuration(flightUpdate.duration());
        flight.setPrice(flightUpdate.price());

        Flight updatedFlight = repository.save(flight);

        return flightResponseDTO(updatedFlight);

    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private FlightResponseDTO flightResponseDTO(Flight flight) {

        return new FlightResponseDTO(
                flight.getId(),
                flight.getNumber(),
                flight.getOrigin(),
                flight.getDestiny(),
                flight.getDeparture(),
                flight.getArrival(),
                flight.getDuration(),
                flight.getPrice(),
                flight.getAvailableSeats().stream()
                        .map(seat -> new SeatResponseDTO(
                                seat.getId(),
                                seat.getNumber(),
                                seat.getType(),
                                seat.getStatus()
                        )).collect(Collectors.toSet())
        );
    }
}
