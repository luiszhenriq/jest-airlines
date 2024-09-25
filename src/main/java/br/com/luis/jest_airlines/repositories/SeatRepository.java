package br.com.luis.jest_airlines.repositories;

import br.com.luis.jest_airlines.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SeatRepository extends JpaRepository<Seat, UUID> {
}
