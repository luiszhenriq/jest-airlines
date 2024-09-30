package br.com.luis.jest_airlines.infra.exception;

import org.springframework.validation.FieldError;

public record ValidationError(String field, String message) {

    public ValidationError(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
