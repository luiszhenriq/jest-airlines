package br.com.luis.jest_airlines.service;

import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationResponseDTO;
import br.com.luis.jest_airlines.infra.exception.IdNotFoundException;
import br.com.luis.jest_airlines.model.Flight;
import br.com.luis.jest_airlines.model.Reservation;
import br.com.luis.jest_airlines.model.Seat;
import br.com.luis.jest_airlines.model.User;
import br.com.luis.jest_airlines.model.enums.PaymentMethod;
import br.com.luis.jest_airlines.model.enums.ReservationStatus;
import br.com.luis.jest_airlines.repositories.FlightRepository;
import br.com.luis.jest_airlines.repositories.ReservationRepository;
import br.com.luis.jest_airlines.repositories.SeatRepository;
import br.com.luis.jest_airlines.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private SeatRepository seatRepository;

    @Mock
    private ReservationRepository repository;

    @InjectMocks
    private ReservationService service;

    private ReservationRequestDTO reservationRequest;

    private Reservation reservation;

    private User user;

    private Flight flight;

    private Seat seat;


    @BeforeEach
    void setUp() {
        user = new User(UUID.randomUUID(), "luis", "luis@gmail.com", "122345",
                LocalDate.of(2004, 6, 16), "1234567899", "71948383848");

        flight = new Flight(UUID.randomUUID(), "LH334", "Salvador", "São Paulo",
                LocalDateTime.of(2024, 7, 20, 20, 0, 0),
                LocalDateTime.of(2024, 7, 22, 0, 0), "2:00", 1500, null);

        seat = new Seat (UUID.randomUUID(), "12A", "economica", "PENDENTE", flight);

        reservation = new Reservation(UUID.randomUUID(), user, flight, LocalDateTime.now(), Set.of(seat),
                flight.getPrice(), ReservationStatus.PENDENTE, PaymentMethod.CREDITO);

        reservationRequest = new ReservationRequestDTO(user.getId(), flight.getId(), Set.of(seat.getId()));

    }

    @Test
    @DisplayName("Should return a created reservation with success")
    void shouldReturnACreatedReservationWithSuccess() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(flightRepository.findById(flight.getId())).thenReturn(Optional.of(flight));
        when(seatRepository.findById(seat.getId())).thenReturn(Optional.of(seat));

        when(repository.save(any())).thenReturn(reservation);

        ReservationResponseDTO response = service.create(reservationRequest);

        assertNotNull(response);
        assertEquals(ReservationResponseDTO.class, response.getClass());
        assertEquals(ReservationStatus.PENDENTE, reservation.getStatus());
        assertEquals(PaymentMethod.CREDITO, reservation.getPaymentMethod());
    }
    @Test
    @DisplayName("When create should return a user id not found exception")
    void whenCreateShouldReturnAUserIdNotFoundException() {
        when(userRepository.findById(user.getId())).thenThrow(new IdNotFoundException("Id não encontrado"));

        try {
            service.create(reservationRequest);
        }catch (Exception ex) {
            assertEquals(IdNotFoundException.class, ex.getClass());
            assertEquals("Id não encontrado", ex.getMessage());
        }
    }

    @Test
    @DisplayName("When create should return an flight id not found exception")
    void whenCreateShouldReturnAnFlightIdNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(flightRepository.findById(flight.getId())).thenThrow(new IdNotFoundException("Id não encontrado"));

        try {
            service.create(reservationRequest);
        }catch (Exception ex) {
            assertEquals(IdNotFoundException.class, ex.getClass());
            assertEquals("Id não encontrado", ex.getMessage());
        }
    }

    @Test
    @DisplayName("When create should return an seat id not found exception")
    void whenCreateShouldReturnAnSeatIdNotFoundException() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(flightRepository.findById(flight.getId())).thenReturn(Optional.of(flight));
        when(seatRepository.findById(seat.getId())).thenThrow(new IdNotFoundException("Id não encontrado"));

        try {
            service.create(reservationRequest);
        }catch (Exception ex) {
            assertEquals(IdNotFoundException.class, ex.getClass());
            assertEquals("Id não encontrado", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Should return a reservation by id with success")
    void shouldReturnAReservationByIdWithSuccess() {
        when(repository.findById(reservation.getId())).thenReturn(Optional.of(reservation));

        ReservationResponseDTO response = service.findById(reservation.getId());

        assertNotNull(response);
        assertEquals(ReservationResponseDTO.class, response.getClass());
        assertEquals(reservation.getId(), response.id());
    }

    @Test
    @DisplayName("Should return an id not found exception")
    void shouldReturnAnIdNotFoundException() {
        when(repository.findById(reservation.getId())).thenThrow(new IdNotFoundException("Id não encontrado"));

        try {
            service.findById(reservation.getId());
        }catch (Exception ex) {
            assertEquals(IdNotFoundException.class, ex.getClass());
            assertEquals("Id não encontrado", ex.getMessage());
        }
    }


    @Test
    @DisplayName("Should cancel a reservation with success")
    void shouldCancelAReservationWithSuccess() {
        doNothing().when(repository).deleteById(reservation.getId());

        service.cancel(reservation.getId());

        verify(repository, times(1)).deleteById(reservation.getId());

    }

}