package br.com.luis.jest_airlines.dto;

import br.com.luis.jest_airlines.model.UserRole;

import java.util.Date;
import java.util.UUID;

public record UserResponseDTO(UUID id,

                              String fullName,

                              String email,

                              String password,

                              Date dateOfBirth,

                              String cpf,

                              String phoneNumber) {
}
