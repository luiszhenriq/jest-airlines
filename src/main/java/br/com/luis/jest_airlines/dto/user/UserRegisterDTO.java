package br.com.luis.jest_airlines.dto.user;


import java.util.Date;

public record UserRegisterDTO(
                             String fullName,

                             String email,

                             String password,

                             Date dateOfBirth,

                             String cpf,

                             String phoneNumber
                             ) {
}
