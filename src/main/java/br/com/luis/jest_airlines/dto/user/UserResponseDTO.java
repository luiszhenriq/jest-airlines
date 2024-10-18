package br.com.luis.jest_airlines.dto.user;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDTO(UUID id,

                              String fullName,

                              String email,

                              LocalDate dateOfBirth,

                              String cpf,

                              String phoneNumber) {
}
