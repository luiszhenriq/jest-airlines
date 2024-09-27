package br.com.luis.jest_airlines.service;


import br.com.luis.jest_airlines.dto.reservation.FlightRespReservationDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationResponseDTO;
import br.com.luis.jest_airlines.dto.reservation.UserRespReservationDTO;
import br.com.luis.jest_airlines.dto.seat.SeatResponseDTO;
import br.com.luis.jest_airlines.infra.exception.IdNotFoundException;
import br.com.luis.jest_airlines.model.Flight;
import br.com.luis.jest_airlines.model.Reservation;
import br.com.luis.jest_airlines.model.Seat;
import br.com.luis.jest_airlines.model.User;
import br.com.luis.jest_airlines.repositories.FlightRepository;
import br.com.luis.jest_airlines.repositories.ReservationRepository;
import br.com.luis.jest_airlines.repositories.SeatRepository;
import br.com.luis.jest_airlines.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {


    private final ReservationRepository repository;
    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final SeatRepository seatRepository;


    public ReservationResponseDTO create(ReservationRequestDTO reservationRequest) {

        User user = userRepository.findById(reservationRequest.userId())
                .orElseThrow(() -> new IdNotFoundException("Id não encontrado"));

        Flight flight = flightRepository.findById(reservationRequest.flightId())
                .orElseThrow(() -> new IdNotFoundException("Id não foi encontrado"));

        Reservation newReservation = new Reservation(reservationRequest);

        reservationRequest.seatsId().forEach(seatId -> {
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(() -> new IdNotFoundException("Id não foi encontrado"));
            newReservation.addSeat(seat);
        });
        newReservation.setUser(user);
        newReservation.setFlight(flight);

        Reservation savedReservation = repository.save(newReservation);

        return reservationResponseDTO(savedReservation);
    }

    public Set<ReservationResponseDTO> getReservationsByUser() {

        return repository.findAllByUser(getAuthenticatedUser())
                .stream()
                .map(this::reservationResponseDTO)
                .collect(Collectors.toSet());
    }

    public void cancel(UUID id) {
        repository.deleteById(id);
    }


    private ReservationResponseDTO reservationResponseDTO(Reservation reservation) {

        return new ReservationResponseDTO(
                reservation.getId(),
                reservation.getDateOfReservation(),
                reservation.getValue(),
                reservation.getStatus(),
                reservation.getPaymentMethod(),
                new UserRespReservationDTO(
                        reservation.getUser().getId(),
                        reservation.getUser().getFullName()
                ),
                new FlightRespReservationDTO(
                        reservation.getFlight().getId(),
                        reservation.getFlight().getNumber(),
                        reservation.getFlight().getOrigin(),
                        reservation.getFlight().getDestiny(),
                        reservation.getFlight().getDeparture(),
                        reservation.getFlight().getArrival(),
                        reservation.getFlight().getDuration()
                ),
                reservation.getReservedSeats().stream()
                        .map(seat -> new SeatResponseDTO(
                                seat.getId(),
                                seat.getNumber(),
                                seat.getType(),
                                seat.getStatus()
                        )).collect(Collectors.toSet())
        );
    }

    private User getAuthenticatedUser() {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String email = userDetails.getUsername();
        return this.userRepository.findUserByEmail(email);
    }
}