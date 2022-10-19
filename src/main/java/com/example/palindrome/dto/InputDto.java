package com.example.palindrome.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InputDto implements Serializable {

    @Size(min = 1, message = "The content should be at least {min} character long.")
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss+0100")
    private LocalDateTime timestamp;
}

