package br.com.luis.jest_airlines.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
        @NotBlank(message = "Este campo não pode ser vazio")
        @Email
        String email,

        @NotBlank(message = "Este campo não pode ser vazio")
        String password
) {
}
