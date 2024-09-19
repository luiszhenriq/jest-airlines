package br.com.luis.jest_airlines.service;


import br.com.luis.jest_airlines.dto.flight.FlightRequestDTO;
import br.com.luis.jest_airlines.dto.flight.FlightResponseDTO;
import br.com.luis.jest_airlines.dto.flight.FlightUpdateDTO;
import br.com.luis.jest_airlines.model.Flight;
import br.com.luis.jest_airlines.repositories.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository repository;


    public FlightResponseDTO create(FlightRequestDTO flightRequest) {

        Flight newFlight = new Flight(flightRequest);

        Flight savedFlight = repository.save(newFlight);

        return flightResponseDTO(savedFlight);
    }

    public List<FlightResponseDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(flight -> new FlightResponseDTO(
                        flight.getId(),
                        flight.getNumber(),
                        flight.getOrigin(),
                        flight.getDestiny(),
                        flight.getDeparture(),
                        flight.getArrival(),
                        flight.getDuration(),
                        flight.getPrice()
                )).collect(Collectors.toList());
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
                flight.getPrice()
        );
    }
}
