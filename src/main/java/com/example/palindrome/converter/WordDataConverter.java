package com.example.palindrome.converter;

import com.example.palindrome.dto.InputDto;
import com.example.palindrome.dto.WordDataDto;
import com.example.palindrome.model.WordData;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WordDataConverter {

    public WordData inputDtoToEntity(InputDto dto) {

        WordData word = new WordData();

        word.setContent(dto.getContent());
        if (dto.getTimestamp() == null) {
            word.setTimestamp(LocalDateTime.now());
        } else {
            word.setTimestamp(dto.getTimestamp());
        }

        return word;
    }

    public WordDataDto entityToDto(WordData entity) {

        WordDataDto dto = new WordDataDto();

        dto.setContent(entity.getContent());
        dto.setTimestamp(entity.getTimestamp());
        dto.setLongestPalindromeSize(entity.getLongestPalindromeSize());

        return dto;
    }
}
