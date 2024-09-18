package br.com.luis.jest_airlines.model;

public enum UserRole {

    USER("user"),
    ADMIN("admin");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
