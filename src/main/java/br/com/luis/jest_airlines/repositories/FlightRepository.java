package br.com.luis.jest_airlines.repositories;

import br.com.luis.jest_airlines.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {
}
