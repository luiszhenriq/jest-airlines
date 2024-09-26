package br.com.luis.jest_airlines.model;


import br.com.luis.jest_airlines.dto.user.UserRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date dateOfBirth;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String phoneNumber;

    public User(UserRequestDTO userRequest) {
        this.fullName = userRequest.fullName();
        this.email = userRequest.email();
        this.password = userRequest.password();
        this.dateOfBirth = userRequest.dateOfBirth();
        this.cpf = userRequest.cpf();
        this.phoneNumber = userRequest.phoneNumber();
    }
}
