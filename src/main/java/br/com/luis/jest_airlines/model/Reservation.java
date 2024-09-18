package br.com.luis.jest_airlines.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

  //  private User user;

  //  private Flight flight;

    private LocalDateTime dateOfReservation;

   // private List<Reservation> reservedSeats = new ArrayList<>();

    @Column(nullable = false)
    private Integer value;

    @Column(nullable = false)
    private String status;

    private String paymentMethod;
}
