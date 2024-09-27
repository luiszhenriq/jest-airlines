package br.com.luis.jest_airlines.infra.exception;

public record ErrorResponse(Long timestamp, Integer status, String error, String message, String path) {
}
