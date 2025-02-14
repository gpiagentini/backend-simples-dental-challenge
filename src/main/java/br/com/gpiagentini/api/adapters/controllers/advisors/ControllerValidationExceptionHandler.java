package br.com.gpiagentini.api.adapters.controllers.advisors;

import br.com.gpiagentini.api.application.dto.ValidatioErrorData;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ControllerValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestBody
    public List<ValidatioErrorData> handleInvalidMethodArgument(MethodArgumentNotValidException ex) {
        return ex.getFieldErrors().stream().map(fieldError -> new ValidatioErrorData(fieldError.getField(), fieldError.getDefaultMessage())).toList();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @RequestBody
    public List<ValidatioErrorData> handleConstraintViolation(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(constraintViolation -> new ValidatioErrorData(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage())).toList();
    }

}
