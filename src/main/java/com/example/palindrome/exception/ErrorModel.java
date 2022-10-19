package com.example.palindrome.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {

    private HttpStatus httpStatus;
    private String fieldName;
    private Object rejectedValue;
    private String errorMessage;
}
