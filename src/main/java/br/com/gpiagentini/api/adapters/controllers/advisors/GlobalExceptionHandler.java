package br.com.gpiagentini.api.adapters.controllers.advisors;

import br.com.gpiagentini.api.application.dto.ValidationErrorData;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNoSuchElementException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErrorData> handleInvalidMethodArgument(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors().stream().map(fieldError -> new ValidationErrorData(fieldError.getField(), fieldError.getDefaultMessage())).toList();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ValidationErrorData> handleConstraintViolation(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(constraintViolation -> new ValidationErrorData(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage())).toList();
    }
}
