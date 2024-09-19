package br.com.luis.jest_airlines.dto;

public record UserUpdateDTO(
        String fullName,

        String email,

        String password,

        String phoneNumber
        ) {
}
