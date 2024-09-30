package br.com.luis.jest_airlines.infra.exception;

public class EmailAlreadyRegisteredException extends RuntimeException{

    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
