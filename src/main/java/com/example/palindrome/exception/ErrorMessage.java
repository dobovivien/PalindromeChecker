package com.example.palindrome.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorMessage {

    BAD_DATE_FORMAT("The date format is not accepted. The correct format is: yyyy-MM-dd HH:mm:ss+0100", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus httpStatus;
}

