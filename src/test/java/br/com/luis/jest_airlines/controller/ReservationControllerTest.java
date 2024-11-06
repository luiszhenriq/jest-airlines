package br.com.luis.jest_airlines.controller;

import br.com.luis.jest_airlines.dto.reservation.FlightRespReservationDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
import br.com.luis.jest_airlines.dto.reservation.ReservationResponseDTO;
import br.com.luis.jest_airlines.dto.reservation.UserRespReservationDTO;
import br.com.luis.jest_airlines.dto.seat.SeatResponseDTO;
import br.com.luis.jest_airlines.model.enums.PaymentMethod;
import br.com.luis.jest_airlines.model.enums.ReservationStatus;
import br.com.luis.jest_airlines.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @InjectMocks
    private ReservationController controller;

    @Mock
    private ReservationService service;

    private ReservationResponseDTO reservationResponse;

    private ReservationRequestDTO reservationRequest;

    private UserRespReservationDTO userRespReservation;

    private FlightRespReservationDTO flightRespReservation;

    private SeatResponseDTO seat;

    @BeforeEach
    void setUp() {

        userRespReservation = new UserRespReservationDTO(UUID.randomUUID(), "luis");

        flightRespReservation = new FlightRespReservationDTO(UUID.randomUUID(), "LH334", "Salvador", "São Paulo",
                LocalDateTime.of(2024, 7, 20, 20, 0, 0),
                LocalDateTime.of(2024, 7, 22, 0, 0), "2:00");

        seat = new SeatResponseDTO(UUID.randomUUID(), "12A", "economica", "PENDENTE");

        reservationResponse = new ReservationResponseDTO(UUID.randomUUID(), LocalDateTime.now(), 1500,
                ReservationStatus.PENDENTE, PaymentMethod.CREDITO, userRespReservation, flightRespReservation, Set.of(seat));

        reservationRequest = new ReservationRequestDTO(userRespReservation.id(), flightRespReservation.id(), Set.of(seat.id()));
    }

    @Test
    @DisplayName("Should return a created reservation with success")
    void shouldReturnACreatedReservationWithSuccess() {
        when(service.create(any())).thenReturn(reservationResponse);

        ResponseEntity<ReservationResponseDTO> response = controller.create(reservationRequest);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    @DisplayName("Should cancel a reservation with success")
    void shouldCancelAReservationWithSuccess() {
        doNothing().when(service).cancel(reservationResponse.id());

        ResponseEntity<Void> response = controller.cancel(reservationResponse.id());

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).cancel(reservationResponse.id());
    }

    @Test
    @DisplayName("should return a list of user reservations")
    void ShouldReturnAListOfUserReservations() {
        when(service.getReservationsByUser()).thenReturn(Set.of(reservationResponse));

        ResponseEntity<Set<ReservationResponseDTO>> response = controller.getAllReservationsByUser();

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}