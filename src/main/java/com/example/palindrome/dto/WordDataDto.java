package com.example.palindrome.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WordDataDto implements Serializable {

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss+0100")
    private LocalDateTime timestamp;

    @JsonProperty("longest_palindrome_size")
    private int longestPalindromeSize;
}
