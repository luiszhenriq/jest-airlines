package br.com.luis.jest_airlines.repositories;

import br.com.luis.jest_airlines.model.Reservation;
import br.com.luis.jest_airlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    Set<Reservation> findAllByUser(User user);
}
