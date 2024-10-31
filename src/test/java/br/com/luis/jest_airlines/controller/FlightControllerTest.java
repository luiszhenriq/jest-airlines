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
import static org.mockito.Mockito.*;

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
    @DisplayName("Should return a flight by id with success")
    void shouldReturnAFlightByIdWithSuccess() {
        when(service.findById(ID)).thenReturn(flightResponse);

        ResponseEntity<FlightResponseDTO> response = controller.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(FlightResponseDTO.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Should update a flight with success")
    void shouldUpdateAFlightWithSuccess() {
        when(service.update(ID, flightUpdate)).thenReturn(flightResponse);

        ResponseEntity<FlightResponseDTO> response = controller.update(ID, flightUpdate);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(FlightResponseDTO.class, response.getBody().getClass());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    @DisplayName("Should delete a flight with success")
    void shouldDeleteAFlightWithSuccess() {
        doNothing().when(service).delete(ID);

        ResponseEntity<Void> response = controller.delete(ID);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(service, times(1)).delete(ID);
    }

}