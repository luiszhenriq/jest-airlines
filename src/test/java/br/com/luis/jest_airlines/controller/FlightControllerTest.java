package br.com.luis.jest_airlines.controller;

import br.com.luis.jest_airlines.dto.flight.FlightResponseDTO;
import br.com.luis.jest_airlines.dto.flight.FlightUpdateDTO;
import br.com.luis.jest_airlines.model.Flight;
import br.com.luis.jest_airlines.model.Seat;
import br.com.luis.jest_airlines.service.FlightService;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    public static final UUID ID = UUID.randomUUID();
    public static final String NUMBER = "LH334";
    public static final String ORIGIN = "Salvador";
    public static final String DESTINY = "São Paulo";
    public static final LocalDateTime DEPARTURE = LocalDateTime.of(2024, 7, 20, 20, 0, 0);
    public static final LocalDateTime ARRIVAL = LocalDateTime.of(2024, 7, 22, 0, 0);
    public static final String DURATION = "2:00";
    public static final int PRICE = 1500;

    @InjectMocks
    private FlightController controller;

    @Mock
    private FlightService service;

    private Flight flight;

    private FlightUpdateDTO flightUpdate;

    private FlightResponseDTO flightResponse;

    private Set<Seat> seats;

    @BeforeEach
    void setUp() {
        flight = new Flight(ID, NUMBER, ORIGIN, DESTINY, DEPARTURE, ARRIVAL, DURATION, PRICE, null);
        flightResponse = new FlightResponseDTO(ID, NUMBER, ORIGIN, DESTINY, DEPARTURE, ARRIVAL, DURATION, PRICE, null);
        flightUpdate = new FlightUpdateDTO(DEPARTURE, ARRIVAL, DURATION, PRICE);
    }

    @Test
    @DisplayName("Should return a flight list")
    void ShouldReturnAFlightList() {
        when(service.findAll()).thenReturn(Set.of(flightResponse));

        ResponseEntity<Set<FlightResponseDTO>> response = controller.findAll();

        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    void setUpSeats() {
        Seat seat1 = mock(Seat.class);
        Seat seat2 = mock(Seat.class);

        when(seat1.getNumber()).thenReturn("1A");
        when(seat2.getNumber()).thenReturn("1B");

        seats = new HashSet<>(Arrays.asList(seat1, seat2));
        flight.setAvailableSeats(seats);
    }
}