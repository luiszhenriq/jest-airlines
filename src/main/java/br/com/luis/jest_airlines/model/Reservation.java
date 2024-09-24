package br.com.luis.jest_airlines.model;


import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
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
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    private LocalDateTime dateOfReservation;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reservation_id")
    private Set<Seat> reservedSeats = new HashSet<>();

    private Integer value;

    @Column(nullable = false)
    private String status;

    private String paymentMethod;

    public Reservation(ReservationRequestDTO reservationRequest) {
        this.dateOfReservation = LocalDateTime.now();
        this.value = reservationRequest.value();
        this.status = reservationRequest.status();
        this.paymentMethod = reservationRequest.paymentMethod();
    }
}
