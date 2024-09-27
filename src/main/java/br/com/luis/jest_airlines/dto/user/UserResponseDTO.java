package br.com.luis.jest_airlines.dto.user;

import java.util.Date;
import java.util.UUID;

public record UserResponseDTO(UUID id,

                              String fullName,

                              String email,

                              Date dateOfBirth,

                              String cpf,

                              String phoneNumber) {
}
