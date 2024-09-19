package br.com.luis.jest_airlines.dto;


import java.util.Date;

public record UserRequestDTO(
                             String fullName,

                             String email,

                             String password,

                             Date dateOfBirth,

                             String cpf,

                             String phoneNumber
                             ) {
}
