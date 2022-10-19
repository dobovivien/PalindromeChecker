package com.example.palindrome.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    private List<ErrorModel> errorMessage;

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionHandler handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<ErrorModel> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorModel(HttpStatus.BAD_REQUEST, error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        return GlobalExceptionHandler.builder().errorMessage(errorMessages).build();
    }

    @ExceptionHandler(value = InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GlobalExceptionHandler handleDateFormatException(InvalidFormatException exception) {

        ErrorModel errorModel = new ErrorModel(
                ErrorMessage.BAD_DATE_FORMAT.getHttpStatus(),
                "timestamp",
                exception.getValue(),
                ErrorMessage.BAD_DATE_FORMAT.getMessage());

        List<ErrorModel> errorMessages = new ArrayList<>();
        errorMessages.add(errorModel);

        return GlobalExceptionHandler.builder().errorMessage(errorMessages).build();
    }
}
