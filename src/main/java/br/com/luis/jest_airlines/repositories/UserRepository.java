package br.com.luis.jest_airlines.repositories;

import br.com.luis.jest_airlines.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    Optional<User> findByEmail(String email);
}
