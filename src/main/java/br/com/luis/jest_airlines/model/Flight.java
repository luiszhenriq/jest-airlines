package br.com.luis.jest_airlines.model;


import br.com.luis.jest_airlines.dto.flight.FlightRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destiny;

    @Column(nullable = false)
    private LocalDateTime departure;

    @Column(nullable = false)
    private LocalDateTime arrival;

    @Column(nullable = false)
    private String duration;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @OneToMany(mappedBy = "flight", cascade = CascadeType.ALL)
    private Set<Seat> availableSeats = new HashSet<>();

    public Flight(FlightRequestDTO flightRequest) {
        this.number = flightRequest.number();
        this.origin = flightRequest.origin();
        this.destiny = flightRequest.destiny();
        this.departure = flightRequest.departure();
        this.arrival = flightRequest.arrival();
        this.duration = flightRequest.duration();
        this.price = flightRequest.price();
    }

}
