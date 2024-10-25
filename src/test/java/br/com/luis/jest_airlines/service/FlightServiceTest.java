package br.com.luis.jest_airlines.service;

import br.com.luis.jest_airlines.dto.flight.FlightResponseDTO;
import br.com.luis.jest_airlines.model.Flight;
import br.com.luis.jest_airlines.model.Seat;
import br.com.luis.jest_airlines.repositories.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    public static final UUID ID = UUID.randomUUID();
    public static final String NUMBER = "LH334";
    public static final String ORIGIN = "Salvador";
    public static final String DESTINY = "São Paulo";
    public static final LocalDateTime DEPARTURE = LocalDateTime.of(2024, 7, 20, 20, 0, 0);
    public static final LocalDateTime ARRIVAL = LocalDateTime.of(2024, 7, 22, 0, 0);
    public static final String DURATION = "2:00";
    public static final int PRICE = 1500;

    @InjectMocks
    private FlightService service;

    @Mock
    private FlightRepository repository;

    private Flight flight;

    private Set<Seat> seats;

    @BeforeEach
    void setUp() {
        Seat seat1 = mock(Seat.class);
        Seat seat2 = mock(Seat.class);

        when(seat1.getNumber()).thenReturn("1A");
        when(seat2.getNumber()).thenReturn("1B");

        seats = new HashSet<>(Arrays.asList(seat1, seat2));
        flight = new Flight(ID, NUMBER, ORIGIN, DESTINY, DEPARTURE, ARRIVAL, DURATION, PRICE, seats);
    }

    @Test
    @DisplayName("should return a flight by id with success")
    void shouldReturnAFlightByIdWithSuccess() {
        when(repository.findById(ID)).thenReturn(Optional.of(flight));

        FlightResponseDTO response = service.findById(ID);

        assertNotNull(response);
        assertEquals(FlightResponseDTO.class, response.getClass());
        assertEquals(ID, response.id());
    }

}