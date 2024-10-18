package br.com.luis.jest_airlines.dto.user;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UserRegisterDTO(
                             @NotBlank(message = "Este campo não pode ser vazio")
                             String fullName,

                             @NotBlank(message = "Este campo não pode ser vazio")
                             @Email
                             String email,

                             @NotBlank(message = "Este campo não pode ser vazio")
                             String password,

                             @NotNull(message = "Este campo não pode ser nulo")
                             LocalDate dateOfBirth,

                             @NotBlank(message = "Este campo não pode ser vazio")
                             String cpf,

                             @NotBlank(message = "Este campo não pode ser vazio")
                             String phoneNumber
                             ) {
}
