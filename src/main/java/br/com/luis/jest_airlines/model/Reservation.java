package br.com.luis.jest_airlines.model;


import br.com.luis.jest_airlines.dto.reservation.ReservationRequestDTO;
import br.com.luis.jest_airlines.model.enums.PaymentMethod;
import br.com.luis.jest_airlines.model.enums.ReservationStatus;
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

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    public Reservation(ReservationRequestDTO reservationRequest) {
        this.dateOfReservation = LocalDateTime.now();
        this.status = reservationRequest.status();
        this.paymentMethod = reservationRequest.paymentMethod();
    }

    public void addSeat(Seat seat) {
        this.reservedSeats.add(seat);
    }
}
