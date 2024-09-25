package br.com.luis.jest_airlines.model;


import br.com.luis.jest_airlines.dto.seat.SeatRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "seats")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;


    public Seat(SeatRequestDTO seatRequest) {
        this.number = seatRequest.number();
        this.type = seatRequest.type();
        this.status = seatRequest.status();
    }
}
