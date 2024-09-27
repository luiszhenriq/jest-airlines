package br.com.luis.jest_airlines.repositories;

import br.com.luis.jest_airlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    UserDetails findByEmail(String email);

    User findUserByEmail(String email);
}
