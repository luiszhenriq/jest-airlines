package br.com.luis.jest_airlines.infra.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ThreatExceptionHandler {

    @ExceptionHandler(IdNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(IdNotFoundException ex, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse error = new ErrorResponse(System.currentTimeMillis(), status.value(), "Não encontrado", ex.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<List<ValidationError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        List<ValidationError> errors = ex.getFieldErrors()
                .stream()
                .map(ValidationError::new)
                .toList();

        return ResponseEntity.badRequest().body(errors);
    }
}
