package com.example.palindrome.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class WordData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Size(min = 1, message = "The content should be at least {min} character long.")
    private String content;

    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss+0100")
    private LocalDateTime timestamp;

    @Column
    @PositiveOrZero
    private int longestPalindromeSize;
}
