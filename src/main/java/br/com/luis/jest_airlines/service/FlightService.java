package br.com.luis.jest_airlines.service;


import br.com.luis.jest_airlines.dto.flight.FlightRequestDTO;
import br.com.luis.jest_airlines.dto.flight.FlightResponseDTO;
import br.com.luis.jest_airlines.dto.flight.FlightUpdateDTO;
import br.com.luis.jest_airlines.dto.seat.SeatResponseDTO;
import br.com.luis.jest_airlines.model.Flight;
import br.com.luis.jest_airlines.model.Seat;
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


    public FlightResponseDTO create(FlightRequestDTO flightRequest) {

        Flight newFlight = new Flight(flightRequest);

        Set<Seat> seats = flightRequest.availableSeats().stream()
                .map(seatRequestDTO -> {
                    Seat seat = new Seat();
                    seat.setNumber(seatRequestDTO.number());
                    seat.setType(seatRequestDTO.type());
                    seat.setStatus(seatRequestDTO.status());
                    seat.setFlight(newFlight);
                    return seat;
                }).collect(Collectors.toSet());

        newFlight.setAvailableSeats(seats);

        Flight savedFlight = repository.save(newFlight);

        return flightResponseDTO(savedFlight);
    }

    public Set<FlightResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::flightResponseDTO)
                .collect(Collectors.toSet());
    }

    public FlightResponseDTO findById(UUID id) {

        Flight flight = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id não encontrado"));

        return flightResponseDTO(flight);
    }

    public FlightResponseDTO update(UUID id, FlightUpdateDTO flightUpdate) {

        Flight flight = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id não encontrado"));

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
